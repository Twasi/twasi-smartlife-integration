package net.twasidependency.smartlife.remote.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TuyaDeviceDTO {

    private String id;
    private String uid;

    @SerializedName("local_key")
    private String localKey;

    private String category;

    @SerializedName("product_id")
    private String productId;

    private boolean sub;
    private String uuid;

    @SerializedName("owner_id")
    private String ownerId;

    private boolean online;
    private String name;
    private String ip;

    @SerializedName("time_zone")
    private String timeZone;

    @SerializedName("create_time")
    private String createTime;

    @SerializedName("update_time")
    private String updateTime;

    @SerializedName("active_time")
    private String activeTime;

    private List<StatusDTO> status;

    public TuyaDeviceDTO(String id, String uid, String localKey, String category, String productId, boolean sub, String uuid, String ownerId, boolean online, String name, String ip, String timeZone, String createTime, String updateTime, String activeTime, List<StatusDTO> status) {
        this.id = id;
        this.uid = uid;
        this.localKey = localKey;
        this.category = category;
        this.productId = productId;
        this.sub = sub;
        this.uuid = uuid;
        this.ownerId = ownerId;
        this.online = online;
        this.name = name;
        this.ip = ip;
        this.timeZone = timeZone;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.activeTime = activeTime;
        this.status = status;
    }

    public static TuyaDeviceDTO fromJson(JsonElement json) {
        return new Gson().fromJson(json, TuyaDeviceDTO.class);
    }

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getLocalKey() {
        return localKey;
    }

    public String getCategory() {
        return category;
    }

    public String getProductId() {
        return productId;
    }

    public boolean isSub() {
        return sub;
    }

    public String getUuid() {
        return uuid;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public boolean isOnline() {
        return online;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public List<StatusDTO> getStatus() {
        return status;
    }

    private static class StatusDTO {

        private String code;
        private String value;

        private StatusDTO(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
}
