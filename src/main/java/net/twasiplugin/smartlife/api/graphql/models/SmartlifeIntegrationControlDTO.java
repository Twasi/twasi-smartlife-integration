package net.twasiplugin.smartlife.api.graphql.models;

import net.twasi.core.database.models.User;
import net.twasi.core.graphql.TwasiGraphQLHandledException;
import net.twasiplugin.smartlife.SmartlifeIntegration;
import net.twasiplugin.smartlife.exceptions.NoSmartlifeAccountAuthenticatedException;
import net.twasiplugin.smartlife.remote.responses.scenes.TriggerSceneResponse;
import net.twasiplugin.smartlife.services.SmartlifeIntegrationService;

import java.util.List;

public class SmartlifeIntegrationControlDTO {

    private User user;
    private SmartlifeIntegrationService service = SmartlifeIntegrationService.get();

    public SmartlifeIntegrationControlDTO(User user) {
        this.user = user;
    }

    public List<TuyaDeviceDTO> getDevices() {
        try {
            return service.getDevicesByUser(user);
        } catch (NoSmartlifeAccountAuthenticatedException e) {
            throw new TwasiGraphQLHandledException("No smartlife account conntected.", "NO_SMARTLIFE_ACCOUNT_CONNECTED");
        } catch (Exception e) {
            SmartlifeIntegration.LOGGER.debug(e);
            return null;
        }
    }

    public List<TuyaHomeDTO> getHomes() {
        try {
            return service.getHomesByUser(user);
        } catch (NoSmartlifeAccountAuthenticatedException e) {
            throw new TwasiGraphQLHandledException("No smartlife account conntected.", "NO_SMARTLIFE_ACCOUNT_CONNECTED");
        } catch (Exception e) {
            SmartlifeIntegration.LOGGER.debug(e);
            return null;
        }
    }

    public List<TuyaSceneDTO> getScenes(long homeId) {
        try {
            return service.getScenesByUserAndHomeId(user, homeId);
        } catch (NoSmartlifeAccountAuthenticatedException e) {
            throw new TwasiGraphQLHandledException("No smartlife account conntected.", "NO_SMARTLIFE_ACCOUNT_CONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
            SmartlifeIntegration.LOGGER.debug(e);
            return null;
        }
    }

    public TriggerSceneResponse triggerScene(long homeId, String sceneId) {
        try {
            return service.triggerSceneByUserAndHome(user, homeId, sceneId);
        } catch (NoSmartlifeAccountAuthenticatedException e) {
            throw new TwasiGraphQLHandledException("No smartlife account conntected.", "NO_SMARTLIFE_ACCOUNT_CONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
            SmartlifeIntegration.LOGGER.debug(e);
            return null;
        }
    }

}
