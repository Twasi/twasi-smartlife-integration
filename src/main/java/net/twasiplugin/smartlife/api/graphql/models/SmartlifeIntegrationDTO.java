package net.twasiplugin.smartlife.api.graphql.models;

import net.twasi.core.database.models.User;
import net.twasi.core.services.providers.config.ConfigService;

public class SmartlifeIntegrationDTO {

    private User user;

    public SmartlifeIntegrationDTO(User user) {
        this.user = user;
    }

    public SmartlifeIntegrationControlDTO getControl() {
        return new SmartlifeIntegrationControlDTO(user);
    }

    public String getAuthenticationUri() {
        return ConfigService.get().getCatalog().webinterface.self + "/oauth/smartlife";
    }
}
