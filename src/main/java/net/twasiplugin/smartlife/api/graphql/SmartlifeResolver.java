package net.twasiplugin.smartlife.api.graphql;

import net.twasi.core.database.models.User;
import net.twasi.core.graphql.TwasiCustomResolver;
import net.twasiplugin.smartlife.api.graphql.models.SmartlifeIntegrationDTO;

public class SmartlifeResolver extends TwasiCustomResolver {

    public SmartlifeIntegrationDTO getSmartlifeIntegration(String token) {
        return new SmartlifeIntegrationDTO(getUser(token));
    }

}
