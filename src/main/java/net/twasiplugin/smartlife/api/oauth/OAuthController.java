package net.twasiplugin.smartlife.api.oauth;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.twasi.core.api.oauth.IOauthIntegrationHandler;
import net.twasi.core.database.models.User;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsDTO;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsRepo;
import net.twasiplugin.smartlife.remote.requests.GetTokenRequest;
import org.apache.http.client.fluent.Response;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static net.twasiplugin.smartlife.SmartlifeIntegration.CONFIG;

public class OAuthController implements IOauthIntegrationHandler {

    private SmartlifeCredentialsRepo repo = DataService.get().get(SmartlifeCredentialsRepo.class);

    public String getOauthServiceName() {
        return "smartlife";
    }

    public String getOauthUri(String state) {
        return String.format("%s/login?client_id=%s&redirect_uri=%s&app_schema=%s", CONFIG.apiUri, CONFIG.clientId, CONFIG.redirectUri, CONFIG.appSchema);
    }

    public void handleResponse(Map<String, String[]> params, User user) {
        boolean success;
        try {
            Response result = new GetTokenRequest(params.get("code")[0]).execute();
            if (result.returnResponse().getStatusLine().getStatusCode() != 200)
                throw new Exception("Bad Request");

            String body = result.returnContent().asString();
            JsonObject ob = new JsonParser().parse(body).getAsJsonObject();

            if (!ob.get("success").getAsBoolean())
                throw new Exception("OAuth unsuccessful");

            ob = ob.get("result").getAsJsonObject();
            String accessToken = ob.get("access_token").getAsString();
            String refreshToken = ob.get("refresh_token").getAsString();
            String uid = ob.get("uid").getAsString();
            int expireTime = ob.get("expire_time").getAsInt();

            Date then = new Date(Calendar.getInstance().getTimeInMillis() + (expireTime - 2) * 60 * 1000);
            SmartlifeCredentialsDTO dto = new SmartlifeCredentialsDTO(user.getId(), uid, accessToken, refreshToken, then);
            repo.add(dto);

            success = true;
        } catch (Exception e) {
            success = false;
        }
        if (!success) {
            // TODO panel message
        }
    }

    public String getStateParameterName() {
        return "state";
    }
}
