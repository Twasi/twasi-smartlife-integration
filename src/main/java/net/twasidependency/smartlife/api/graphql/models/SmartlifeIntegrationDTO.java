package net.twasidependency.smartlife.api.graphql.models;

import net.twasi.core.database.models.User;
import net.twasi.core.graphql.model.PanelResultDTO;
import net.twasi.core.graphql.model.PanelResultDTO.PanelResultType;
import net.twasi.core.services.providers.DataService;
import net.twasi.core.services.providers.config.ConfigService;
import net.twasidependency.smartlife.SmartlifeIntegration;
import net.twasidependency.smartlife.database.credentials.TuyaCredentialsRepo;

public class SmartlifeIntegrationDTO {

    private static TuyaCredentialsRepo repo = DataService.get().get(TuyaCredentialsRepo.class);

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

    public PanelResultDTO disconnect() {
        repo.deleteByUser(user.getId());
        return new PanelResultDTO(PanelResultType.OK);
    }

    public int getMaxSequenceSteps() {
        return SmartlifeIntegration.CONFIG.maxSequenceSteps;
    }
}
