package net.twasiplugin.smartlife.database;

import net.twasi.core.database.lib.Repository;
import net.twasi.core.database.models.User;
import org.bson.types.ObjectId;

import java.util.List;

public class SmartlifeCredentialsRepo extends Repository<SmartlifeCredentialsDTO> {

    public SmartlifeCredentialsDTO getByUser(ObjectId user) {
        List<SmartlifeCredentialsDTO> dtos = query().field("user").equal(user).asList();
        if (dtos.size() == 0) return null;
        return dtos.get(0);
    }

    public SmartlifeCredentialsDTO getByUser(User user) {
        return getByUser(user.getId());
    }

    @Override
    public void add(SmartlifeCredentialsDTO entity) {
        deleteByUser(entity.getUser());
        super.add(entity);
    }

    public void deleteByUser(ObjectId user) {
        SmartlifeCredentialsDTO dto = getByUser(user);
        if (dto != null) remove(dto);
    }
}
