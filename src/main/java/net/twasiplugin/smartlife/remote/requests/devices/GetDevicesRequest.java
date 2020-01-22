package net.twasiplugin.smartlife.remote.requests.devices;

import net.twasiplugin.smartlife.remote.models.TuyaDeviceDTO;
import net.twasiplugin.smartlife.database.credentials.TuyaCredentialsDTO;
import net.twasiplugin.smartlife.remote.TuyaRequestBuilder;
import net.twasiplugin.smartlife.remote.responses.devices.GetDevicesResponse;

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
