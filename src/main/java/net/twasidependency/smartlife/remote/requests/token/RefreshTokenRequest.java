package net.twasidependency.smartlife.remote.requests.token;

import com.google.gson.Gson;
import net.twasidependency.smartlife.remote.responses.token.TokenResponse;
import net.twasidependency.smartlife.remote.TuyaRequestBuilder;

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
