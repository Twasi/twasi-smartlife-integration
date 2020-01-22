package net.twasiplugin.smartlife.remote.requests.scenes;

import com.google.gson.JsonElement;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsDTO;
import net.twasiplugin.smartlife.remote.TuyaRequestBuilder;
import net.twasiplugin.smartlife.remote.responses.scenes.TriggerSceneResponse;
import org.apache.http.client.fluent.Response;

import java.io.IOException;

public class TriggerSceneRequest extends TuyaRequestBuilder<TriggerSceneResponse> {

    public TriggerSceneRequest(SmartlifeCredentialsDTO credentialsDTO, String sceneId, long homeId) {
        super("/v1.0/homes/" + homeId + "/scenes/" + sceneId + "/trigger");
        withAuthToken(credentialsDTO.getAccessToken());
        asPost();
    }

    @Override
    public TriggerSceneResponse executeAndGet() throws IOException {
        Response execute = execute();
        JsonElement result = extractResult(execute);
        System.out.println(result.toString());
        return new TriggerSceneResponse(result.getAsBoolean());
    }
}
