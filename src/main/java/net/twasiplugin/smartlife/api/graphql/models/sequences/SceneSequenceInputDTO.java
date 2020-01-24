package net.twasiplugin.smartlife.api.graphql.models.sequences;

import java.util.List;

public class SceneSequenceInputDTO {

    private String name;
    private String variable;
    private List<SceneSequenceStepDTO> steps;

    public SceneSequenceInputDTO(String name, String variable, List<SceneSequenceStepDTO> steps) {
        this.name = name;
        this.variable = variable;
        this.steps = steps;
    }

    public SceneSequenceInputDTO() {
    }

    public String getName() {
        return name;
    }

    public String getVariable() {
        return variable;
    }

    public List<SceneSequenceStepDTO> getSteps() {
        return steps;
    }
}
