package net.twasiplugin.smartlife.remote.responses.token;

public class TokenResponse {

    private String uid;
    private String access_token;
    private String refresh_token;
    private int expire_time;

    public TokenResponse(String uid, String access_token, String refresh_token, int expire_time) {
        this.uid = uid;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.expire_time = expire_time;
    }

    public String getUid() {
        return uid;
    }

    public String getAccessToken() {
        return access_token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public int getExpireTime() {
        return expire_time;
    }
}
