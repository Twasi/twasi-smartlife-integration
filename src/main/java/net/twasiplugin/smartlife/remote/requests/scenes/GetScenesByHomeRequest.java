package net.twasiplugin.smartlife.remote.requests.scenes;

import net.twasiplugin.smartlife.api.graphql.models.TuyaSceneDTO;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsDTO;
import net.twasiplugin.smartlife.remote.TuyaRequestBuilder;
import net.twasiplugin.smartlife.remote.responses.scenes.GetScenesResponse;

import java.io.IOException;

public class GetScenesByHomeRequest extends TuyaRequestBuilder<GetScenesResponse> {

    public GetScenesByHomeRequest(SmartlifeCredentialsDTO credentialsDTO, long homeId) {
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
