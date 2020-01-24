package net.twasidependency.smartlife.remote.requests.token;

import com.google.gson.Gson;
import net.twasidependency.smartlife.remote.responses.token.TokenResponse;
import net.twasidependency.smartlife.remote.TuyaRequestBuilder;

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
