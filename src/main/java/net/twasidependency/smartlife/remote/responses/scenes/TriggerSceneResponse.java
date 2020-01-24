package net.twasidependency.smartlife.remote.responses.scenes;

public class TriggerSceneResponse {

    private boolean success;

    public TriggerSceneResponse(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }
}
