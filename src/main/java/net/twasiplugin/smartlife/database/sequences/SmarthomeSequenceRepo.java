package net.twasiplugin.smartlife.database.sequences;

import net.twasi.core.database.lib.Repository;
import net.twasi.core.database.models.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

public class SmarthomeSequenceRepo extends Repository<SmarthomeSequenceDTO> {

    public List<SmarthomeSequenceDTO> getByUserPaginated(int page, User user) {
        return query().field("user").equal(user.getId()).asList(paginated(page));
    }

    public long countByUser(User user) {
        return query().field("user").equal(user.getId()).count();
    }

    public SmarthomeSequenceDTO getByUserAndVariable(User user, String variable) {
        try {
            return query().field("user").equal(user.getId()).field("variable").equal(variable.toLowerCase()).get();
        } catch (Exception e) {
            return null;
        }
    }

    public SmarthomeSequenceDTO getByUserAndId(User user, ObjectId id) {
        try {
            return query().field("user").equal(user.getId()).field("_id").equal(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public SmarthomeSequenceDTO addAndReturn(SmarthomeSequenceDTO entity) {
        if (entity.getVariable() != null) entity.setVariable(entity.getVariable().toLowerCase());
        String id = (String) this.store.save(entity).getId();
        return getById(id);
    }

    public SmarthomeSequenceDTO commitAndReturn(SmarthomeSequenceDTO entity) {
        if (entity.getVariable() != null) entity.setVariable(entity.getVariable().toLowerCase());
        super.commit(entity);
        return getById(entity.getId());
    }

    public List<String> getVariablesByUser(User user) {
        return query()
                .field("user").equal(user.getId())
                .project("variable", true)
                .asList().stream()
                .map(SmarthomeSequenceDTO::getVariable)
                .collect(Collectors.toList());
    }

    public boolean deleteByUserAndId(User user, String id) {
        return store.delete(getByUserAndId(user, new ObjectId(id))).getN() > 0;
    }
}
