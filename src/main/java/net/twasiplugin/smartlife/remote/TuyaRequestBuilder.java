package net.twasiplugin.smartlife.remote;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static net.twasiplugin.smartlife.SmartlifeIntegration.CONFIG;

public abstract class TuyaRequestBuilder<T> {

    private String accessToken = null;
    private Request request;
    private String path;
    private String t;

    private Map<String, String> headers = new HashMap<>();
    private String body = null;
    private String contentType;

    public TuyaRequestBuilder(String path) {
        this.path = path;
        this.asGet();
    }

    /**
     * Add an auth token to the request
     *
     * @param accessToken The user's access token to the tuya openapi with smartlife app schema
     * @return The builder instance
     */
    public TuyaRequestBuilder<T> withAuthToken(@NotNull String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    /**
     * Add another header to the request
     *
     * @param header The name of the header
     * @param value  The value of the header
     * @return The builder instance
     */
    public TuyaRequestBuilder<T> addHeader(String header, String value) {
        headers.put(header, value);
        return this;
    }

    /**
     * Add a body to the request
     *
     * @param body        The content of the body
     * @param contentType The content type of the body (application/json e.g.)
     * @return the builder instance
     */
    public TuyaRequestBuilder<T> withBody(String body, String contentType) {
        this.body = body;
        this.contentType = contentType;
        return this;
    }

    /**
     * Executes the request and returns the it's result
     *
     * @return The raw result of the request
     * @throws IOException when the request fails
     */
    public Response execute() throws IOException {
        this.t = String.valueOf(Calendar.getInstance().getTimeInMillis());
        request.addHeader("t", t);
        request.addHeader("client_id", CONFIG.clientId);
        request.addHeader("sign_method", "HMAC-SHA256");
        request.addHeader("sign", getSign(t));
        if (this.accessToken != null) request.addHeader("access_token", this.accessToken);

        headers.forEach(request::addHeader);
        if (body != null) request.bodyString(body, ContentType.parse(contentType));

        return request.execute();
    }

    public abstract T executeAndGet() throws IOException;

    /**
     * Generates a request sign for the Tuya Cloud OpenAPI
     *
     * @param t A standard timestamp that is hashed in the sign and added in the header of the request
     * @return The request sign as documented in the Tuya Cloud OpenAPI
     */
    private String getSign(String t) {
        StringBuilder sign = new StringBuilder(CONFIG.clientId);
        if (this.accessToken != null) sign.append(this.accessToken);
        sign.append(t);
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(CONFIG.clientSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return Hex.encodeHexString(sha256_HMAC.doFinal(sign.toString().getBytes(StandardCharsets.UTF_8))).toUpperCase();
        } catch (Exception ignored) {
            return null;
        }
    }

    /***
     * Initializes request as GET request
     * @return The builder instance
     */
    public TuyaRequestBuilder<T> asGet() {
        request = Request.Get(String.format("%s%s", CONFIG.apiUri, path));
        return this;
    }

    /***
     * Initializes request as POST request
     * @return The builder instance
     */
    public TuyaRequestBuilder<T> asPost() {
        request = Request.Post(String.format("%s%s", CONFIG.apiUri, path));
        return this;
    }

    /***
     * Initializes request as PUT request
     * @return The builder instance
     */
    public TuyaRequestBuilder<T> asPut() {
        request = Request.Put(String.format("%s%s", CONFIG.apiUri, path));
        return this;
    }

    protected JsonElement extractResult(Response response) throws IOException {
        return new JsonParser().parse(response.returnContent().asString()).getAsJsonObject().get("result");
    }

}
