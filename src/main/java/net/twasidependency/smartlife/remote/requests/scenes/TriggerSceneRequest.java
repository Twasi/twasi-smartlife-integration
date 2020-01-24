package net.twasidependency.smartlife.remote.requests.scenes;

import com.google.gson.JsonElement;
import net.twasidependency.smartlife.database.credentials.TuyaCredentialsDTO;
import net.twasidependency.smartlife.remote.TuyaRequestBuilder;
import net.twasidependency.smartlife.remote.responses.scenes.TriggerSceneResponse;
import org.apache.http.client.fluent.Response;

import java.io.IOException;

public class TriggerSceneRequest extends TuyaRequestBuilder<TriggerSceneResponse> {

    public TriggerSceneRequest(TuyaCredentialsDTO credentialsDTO, String sceneId, long homeId) {
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
