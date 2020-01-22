package net.twasiplugin.smartlife.api.graphql.models.sequences;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class SceneSequenceStepDTO {

    private long homeId;
    private String sceneId;
    private int msDelay = 0;

    public SceneSequenceStepDTO(long homeId, String sceneId, int msDelay) {
        this.homeId = homeId;
        this.sceneId = sceneId;
        this.msDelay = msDelay;
    }

    public SceneSequenceStepDTO(long homeId, String sceneId) {
        this.homeId = homeId;
        this.sceneId = sceneId;
    }

    public SceneSequenceStepDTO() {
    }

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public int getMsDelay() {
        return msDelay;
    }

    public void setMsDelay(int msDelay) {
        this.msDelay = msDelay;
    }
}
