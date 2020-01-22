package net.twasiplugin.smartlife.api.graphql.models;

import com.google.gson.annotations.SerializedName;

public class TuyaSceneActionDTO {

    @SerializedName("entity_id")
    private String entityId;

    @SerializedName("executor_property")
    private String executorProperty;

    @SerializedName("action_executor")
    private String actionExecutor;

    public TuyaSceneActionDTO(String entityId, String executorProperty, String actionExecutor) {
        this.entityId = entityId;
        this.executorProperty = executorProperty;
        this.actionExecutor = actionExecutor;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getExecutorProperty() {
        return executorProperty;
    }

    public String getActionExecutor() {
        return actionExecutor;
    }
}
