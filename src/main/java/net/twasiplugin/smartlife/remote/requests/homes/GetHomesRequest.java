package net.twasiplugin.smartlife.remote.requests.homes;

import net.twasiplugin.smartlife.remote.models.TuyaHomeDTO;
import net.twasiplugin.smartlife.database.credentials.TuyaCredentialsDTO;
import net.twasiplugin.smartlife.remote.TuyaRequestBuilder;
import net.twasiplugin.smartlife.remote.responses.homes.GetHomesResponse;

import java.io.IOException;

public class GetHomesRequest extends TuyaRequestBuilder<GetHomesResponse> {

    public GetHomesRequest(TuyaCredentialsDTO credentialsDTO) {
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
