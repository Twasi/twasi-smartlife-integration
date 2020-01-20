package net.twasiplugin.smartlife.remote.requests;

import com.google.gson.JsonParser;
import net.twasiplugin.smartlife.api.graphql.models.DeviceDTO;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsDTO;
import net.twasiplugin.smartlife.remote.TuyaRequestBuilder;
import net.twasiplugin.smartlife.remote.responses.GetDevicesResponse;

import java.io.IOException;

public class GetDevicesRequest extends TuyaRequestBuilder<GetDevicesResponse> {

    public GetDevicesRequest(SmartlifeCredentialsDTO credentialsDTO) {
        super("/users/" + credentialsDTO.getUid() + "/devices");
        withAuthToken(credentialsDTO.getAccessToken());
    }

    @Override
    public GetDevicesResponse executeAndGet() throws IOException {
        GetDevicesResponse response = new GetDevicesResponse();
        new JsonParser().parse(execute().returnContent().asString()).getAsJsonObject()
                        .get("result").getAsJsonArray()
                        .forEach(e -> response.add(DeviceDTO.fromJson(e)));
        return response;
    }
}