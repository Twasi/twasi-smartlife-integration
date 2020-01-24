package net.twasidependency.smartlife.remote.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

public class TuyaHomeDTO {

    @SerializedName("home_id")
    private long homeId;

    private String name;

    @SerializedName("geo_name")
    private String geoName;

    private double lon;
    private double lat;

    public TuyaHomeDTO(long homeId, String name, String geoName, double lon, double lat) {
        this.homeId = homeId;
        this.name = name;
        this.geoName = geoName;
        this.lon = lon;
        this.lat = lat;
    }

    public long getHomeId() {
        return homeId;
    }

    public String getName() {
        return name;
    }

    public String getGeoName() {
        return geoName;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public static TuyaHomeDTO fromJson(JsonElement e) {
        return new Gson().fromJson(e, TuyaHomeDTO.class);
    }
}
