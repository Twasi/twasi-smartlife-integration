package net.twasiplugin.smartlife.remote.requests.scenes;

import net.twasiplugin.smartlife.remote.models.TuyaSceneDTO;
import net.twasiplugin.smartlife.database.credentials.TuyaCredentialsDTO;
import net.twasiplugin.smartlife.remote.TuyaRequestBuilder;
import net.twasiplugin.smartlife.remote.responses.scenes.GetScenesResponse;

import java.io.IOException;

public class GetScenesByHomeRequest extends TuyaRequestBuilder<GetScenesResponse> {

    public GetScenesByHomeRequest(TuyaCredentialsDTO credentialsDTO, long homeId) {
        super("/v1.0/homes/" + homeId + "/scenes");
        withAuthToken(credentialsDTO.getAccessToken());
    }

    @Override
    public GetScenesResponse executeAndGet() throws IOException {
        GetScenesResponse response = new GetScenesResponse();
        extractResult(execute()).getAsJsonArray().forEach(e -> response.add(TuyaSceneDTO.fromJson(e)));
        return response;
    }
}
