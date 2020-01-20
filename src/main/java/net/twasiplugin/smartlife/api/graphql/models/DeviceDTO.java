package net.twasiplugin.smartlife.api.graphql.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

public class DeviceDTO {

    private String id;
    private String uid;
    private String local_key;
    private String category;
    private String product_id;
    private boolean sub;
    private String uuid;
    private String owner_id;
    private boolean online;
    private String name;
    private String ip;
    private String time_zone;
    private String create_time;
    private String update_time;
    private String active_time;
    private List<StatusDTO> status;

    public DeviceDTO(String id, String uid, String local_key, String category, String product_id, boolean sub, String uuid, String owner_id, boolean online, String name, String ip, String time_zone, String create_time, String update_time, String active_time, List<StatusDTO> status) {
        this.id = id;
        this.uid = uid;
        this.local_key = local_key;
        this.category = category;
        this.product_id = product_id;
        this.sub = sub;
        this.uuid = uuid;
        this.owner_id = owner_id;
        this.online = online;
        this.name = name;
        this.ip = ip;
        this.time_zone = time_zone;
        this.create_time = create_time;
        this.update_time = update_time;
        this.active_time = active_time;
        this.status = status;
    }

    public static DeviceDTO fromJson(JsonElement json) {
        return new Gson().fromJson(json, DeviceDTO.class);
    }

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getLocal_key() {
        return local_key;
    }

    public String getCategory() {
        return category;
    }

    public String getProduct_id() {
        return product_id;
    }

    public boolean isSub() {
        return sub;
    }

    public String getUuid() {
        return uuid;
    }

    public String getOwner_id() {
        return owner_id;
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

    public String getTime_zone() {
        return time_zone;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getActive_time() {
        return active_time;
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
