package net.twasidependency.smartlife.api.graphql.models.sequences;

import net.twasidependency.smartlife.database.sequences.SmarthomeSequenceDTO;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

@Entity
public class SceneSequenceDTO {

    private String id;
    private String name;
    private String variable = null;
    private long updated;
    private long created;
    private List<SceneSequenceStepDTO> steps;

    public SceneSequenceDTO(String id, String name, String variable, long updated, long created, List<SceneSequenceStepDTO> steps) {
        this.id = id;
        this.name = name;
        this.variable = variable;
        this.updated = updated;
        this.created = created;
        this.steps = steps;
    }

    public SceneSequenceDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public List<SceneSequenceStepDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<SceneSequenceStepDTO> steps) {
        this.steps = steps;
    }

    public static SceneSequenceDTO fromDb(SmarthomeSequenceDTO dto) {
        return new SceneSequenceDTO(
                dto.getId().toString(),
                dto.getName(),
                dto.getVariable(),
                dto.getUpdated().getTime(),
                dto.getCreated().getTime(),
                dto.getSteps()
        );
    }
}
