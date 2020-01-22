package net.twasiplugin.smartlife.database.sequences;

import net.twasi.core.database.lib.Repository;
import net.twasi.core.database.models.User;

import java.util.List;

public class SmarthomeSequenceRepo extends Repository<SmarthomeSequenceDTO> {

    public List<SmarthomeSequenceDTO> getByUserPaginated(int page, User user) {
        return query().field("user").equal(user.getId()).asList(paginated(page));
    }

    public long countByUser(User user) {
        return query().field("user").equal(user.getId()).count();
    }

}
