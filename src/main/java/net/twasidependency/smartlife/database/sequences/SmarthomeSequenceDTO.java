package net.twasidependency.smartlife.database.sequences;

import net.twasi.core.database.models.BaseEntity;
import net.twasi.core.database.models.User;
import net.twasidependency.smartlife.api.graphql.models.sequences.SceneSequenceInputDTO;
import net.twasidependency.smartlife.api.graphql.models.sequences.SceneSequenceStepDTO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class SmarthomeSequenceDTO extends BaseEntity {

    private ObjectId user;
    private String name;
    private String variable;
    private Date updated = Calendar.getInstance().getTime();
    private Date created = updated;
    private List<SceneSequenceStepDTO> steps;

    public SmarthomeSequenceDTO(ObjectId user, String name, String variable, List<SceneSequenceStepDTO> steps) {
        this.user = user;
        this.name = name;
        this.variable = variable;
        this.steps = steps;
    }

    public SmarthomeSequenceDTO() {
    }

    public ObjectId getUser() {
        return user;
    }

    public void setUser(ObjectId user) {
        this.user = user;
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<SceneSequenceStepDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<SceneSequenceStepDTO> steps) {
        this.steps = steps;
    }

    public void applyUpdateFromInput(SceneSequenceInputDTO dto) {
        this.name = dto.getName();
        this.variable = dto.getVariable();
        this.steps = dto.getSteps();
    }

    public static SmarthomeSequenceDTO fromInput(User user, SceneSequenceInputDTO dto) {
        return new SmarthomeSequenceDTO(
                user.getId(),
                dto.getName(),
                dto.getVariable(),
                dto.getSteps()
        );
    }
}
