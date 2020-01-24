package net.twasidependency.smartlife.remote.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

public class TuyaSceneDTO {

    @SerializedName("scene_id")
    private String sceneId;

    private String name;

    private String background;

    public TuyaSceneDTO(String sceneId, String name, String background) {
        this.sceneId = sceneId;
        this.name = name;
        this.background = background;
    }

    public String getSceneId() {
        return sceneId;
    }

    public String getBackground() {
        return background;
    }

    public String getName() {
        return name;
    }

    public static TuyaSceneDTO fromJson(JsonElement element) {
        return new Gson().fromJson(element, TuyaSceneDTO.class);
    }
}
