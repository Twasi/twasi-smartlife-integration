package net.twasidependency.smartlife.api.graphql;

import net.twasi.core.graphql.TwasiCustomResolver;
import net.twasidependency.smartlife.api.graphql.models.SmartlifeIntegrationDTO;

public class SmartlifeResolver extends TwasiCustomResolver {

    public SmartlifeIntegrationDTO getSmartlifeintegration(String token) {
        return new SmartlifeIntegrationDTO(token == null ? null : getUser(token));
    }
}
