package net.twasidependency.smartlife.api.graphql.models;

import net.twasi.core.database.models.User;
import net.twasi.core.graphql.TwasiGraphQLHandledException;
import net.twasi.core.graphql.model.PanelResultDTO;
import net.twasidependency.smartlife.SmartlifeIntegration;
import net.twasidependency.smartlife.exceptions.NoSmartlifeAccountAuthenticatedException;
import net.twasidependency.smartlife.api.graphql.models.sequences.SceneSequencesDTO;
import net.twasidependency.smartlife.remote.models.TuyaDeviceDTO;
import net.twasidependency.smartlife.remote.models.TuyaHomeDTO;
import net.twasidependency.smartlife.remote.models.TuyaSceneDTO;
import net.twasidependency.smartlife.services.SmartlifeIntegrationService;

import java.util.List;

public class SmartlifeIntegrationControlDTO {

    private User user;
    private SmartlifeIntegrationService service = SmartlifeIntegrationService.get();

    public SmartlifeIntegrationControlDTO(User user) {
        this.user = user;
    }

    public SceneSequencesDTO getSceneSequences() {
        return new SceneSequencesDTO(user);
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

    public PanelResultDTO triggerScene(long homeId, String sceneId) {
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
