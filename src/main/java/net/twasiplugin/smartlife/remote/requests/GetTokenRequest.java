package net.twasiplugin.smartlife.remote.requests;

import com.google.gson.Gson;
import net.twasiplugin.smartlife.remote.TuyaRequestBuilder;
import net.twasiplugin.smartlife.remote.responses.TokenResponse;

import java.io.IOException;

public class GetTokenRequest extends TuyaRequestBuilder<TokenResponse> {

    public GetTokenRequest(String code) {
        super("/v1.0/token?grant_type=2&code=" + code);
    }

    @Override
    public TokenResponse executeAndGet() throws IOException {
        return new Gson().fromJson(extractResult(execute()), TokenResponse.class);
    }
}
