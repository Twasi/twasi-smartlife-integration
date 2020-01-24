package net.twasidependency.smartlife.remote.requests.devices;

import net.twasidependency.smartlife.remote.models.TuyaDeviceDTO;
import net.twasidependency.smartlife.database.credentials.TuyaCredentialsDTO;
import net.twasidependency.smartlife.remote.TuyaRequestBuilder;
import net.twasidependency.smartlife.remote.responses.devices.GetDevicesResponse;

import java.io.IOException;

public class GetDevicesRequest extends TuyaRequestBuilder<GetDevicesResponse> {

    public GetDevicesRequest(TuyaCredentialsDTO credentialsDTO) {
        super("/v1.0/users/" + credentialsDTO.getUid() + "/devices");
        withAuthToken(credentialsDTO.getAccessToken());
    }

    @Override
    public GetDevicesResponse executeAndGet() throws IOException {
        GetDevicesResponse response = new GetDevicesResponse();
        extractResult(execute()).getAsJsonArray().forEach(e -> response.add(TuyaDeviceDTO.fromJson(e)));
        return response;
    }
}
