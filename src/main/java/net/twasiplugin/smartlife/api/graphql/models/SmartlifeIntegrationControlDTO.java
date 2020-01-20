package net.twasiplugin.smartlife.api.graphql.models;

import net.twasi.core.database.models.User;
import net.twasi.core.graphql.TwasiGraphQLHandledException;
import net.twasiplugin.smartlife.exceptions.NoSmartlifeAccountAuthenticatedException;
import net.twasiplugin.smartlife.services.SmartlifeIntegrationService;

import java.io.IOException;
import java.util.List;

public class SmartlifeIntegrationControlDTO {

    private User user;
    private SmartlifeIntegrationService service = SmartlifeIntegrationService.get();

    public SmartlifeIntegrationControlDTO(User user) {
        this.user = user;
    }

    public List<DeviceDTO> getDevices() throws IOException {
        try {
            return service.getDevicesByUser(user);
        } catch (NoSmartlifeAccountAuthenticatedException e) {
            throw new TwasiGraphQLHandledException("No smartlife account conntected.", "NO_SMARTLIFE_ACCOUNT_CONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
