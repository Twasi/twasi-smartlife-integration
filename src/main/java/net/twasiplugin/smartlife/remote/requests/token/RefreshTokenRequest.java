package net.twasiplugin.smartlife.remote.requests.token;

import com.google.gson.Gson;
import net.twasiplugin.smartlife.remote.TuyaRequestBuilder;
import net.twasiplugin.smartlife.remote.responses.token.TokenResponse;

import java.io.IOException;

public class RefreshTokenRequest extends TuyaRequestBuilder<TokenResponse> {

    public RefreshTokenRequest(String refreshToken) {
        super(" /v1.0/token/" + refreshToken);
    }

    @Override
    public TokenResponse executeAndGet() throws IOException {
        return new Gson().fromJson(extractResult(execute()), TokenResponse.class);
    }
}
