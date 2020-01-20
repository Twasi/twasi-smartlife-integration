package net.twasiplugin.smartlife.api.graphql;

import net.twasi.core.graphql.TwasiCustomResolver;
import net.twasi.core.services.providers.config.ConfigService;
import net.twasiplugin.smartlife.api.graphql.models.SmartlifeIntegrationControlDTO;
import net.twasiplugin.smartlife.api.graphql.models.SmartlifeIntegrationDTO;

public class SmartlifeResolver extends TwasiCustomResolver {

    public SmartlifeIntegrationDTO getSmartlifeintegration(String token) {
        return new SmartlifeIntegrationDTO(token == null ? null : getUser(token));
    }
}
