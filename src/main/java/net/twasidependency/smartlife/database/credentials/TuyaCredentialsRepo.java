package net.twasidependency.smartlife.database.credentials;

import net.twasi.core.database.lib.Repository;
import net.twasi.core.database.models.User;
import org.bson.types.ObjectId;

import java.util.List;

public class TuyaCredentialsRepo extends Repository<TuyaCredentialsDTO> {

    public TuyaCredentialsDTO getByUser(ObjectId user) {
        List<TuyaCredentialsDTO> dtos = query().field("user").equal(user).asList();
        if (dtos.size() == 0) return null;
        return dtos.get(0);
    }

    public TuyaCredentialsDTO getByUser(User user) {
        return getByUser(user.getId());
    }

    @Override
    public void add(TuyaCredentialsDTO entity) {
        deleteByUser(entity.getUser());
        super.add(entity);
    }

    public void deleteByUser(ObjectId user) {
        TuyaCredentialsDTO dto = getByUser(user);
        if (dto != null) remove(dto);
    }
}
