package net.twasiplugin.smartlife.remote.requests.homes;

import net.twasiplugin.smartlife.api.graphql.models.TuyaHomeDTO;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsDTO;
import net.twasiplugin.smartlife.remote.TuyaRequestBuilder;
import net.twasiplugin.smartlife.remote.responses.homes.GetHomesResponse;

import java.io.IOException;

public class GetHomesRequest extends TuyaRequestBuilder<GetHomesResponse> {

    public GetHomesRequest(SmartlifeCredentialsDTO credentialsDTO) {
        super("/v1.0/users/" + credentialsDTO.getUid() + "/homes");
        withAuthToken(credentialsDTO.getAccessToken());
    }

    @Override
    public GetHomesResponse executeAndGet() throws IOException {
        GetHomesResponse response = new GetHomesResponse();
        extractResult(execute()).getAsJsonArray().forEach(e -> response.add(TuyaHomeDTO.fromJson(e)));
        return response;
    }
}
