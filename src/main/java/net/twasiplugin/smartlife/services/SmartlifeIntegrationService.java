package net.twasiplugin.smartlife.services;

import net.twasi.core.database.models.User;
import net.twasi.core.graphql.model.PanelResultDTO;
import net.twasi.core.graphql.model.PanelResultDTO.PanelResultType;
import net.twasi.core.services.IService;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.smartlife.api.graphql.models.TuyaDeviceDTO;
import net.twasiplugin.smartlife.api.graphql.models.TuyaHomeDTO;
import net.twasiplugin.smartlife.api.graphql.models.TuyaSceneDTO;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsDTO;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsRepo;
import net.twasiplugin.smartlife.exceptions.NoSmartlifeAccountAuthenticatedException;
import net.twasiplugin.smartlife.remote.requests.devices.GetDevicesRequest;
import net.twasiplugin.smartlife.remote.requests.homes.GetHomesRequest;
import net.twasiplugin.smartlife.remote.requests.scenes.GetScenesByHomeRequest;
import net.twasiplugin.smartlife.remote.requests.scenes.TriggerSceneRequest;
import net.twasiplugin.smartlife.remote.requests.token.RefreshTokenRequest;
import net.twasiplugin.smartlife.remote.responses.token.TokenResponse;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartlifeIntegrationService implements IService {

    private static SmartlifeCredentialsRepo repo = DataService.get().get(SmartlifeCredentialsRepo.class);

    private Map<String, SmartlifeCredentialsDTO> cachedCredentials = new HashMap<>();

    public static SmartlifeIntegrationService get() {
        return ServiceRegistry.get(SmartlifeIntegrationService.class);
    }

    private SmartlifeCredentialsDTO getByUser(User user) throws NoSmartlifeAccountAuthenticatedException {
        SmartlifeCredentialsDTO dto;

        if (cachedCredentials.containsKey(user.getId().toString()))
            dto = cachedCredentials.get(user.getId().toString());
        else if ((dto = repo.getByUser(user)) != null)
            cachedCredentials.put(user.getId().toString(), dto);

        if (dto == null) throw new NoSmartlifeAccountAuthenticatedException();

        if (dto.getExpirationDate().getTime() < Calendar.getInstance().getTimeInMillis())
            this.refresh(user, dto);

        return dto;
    }

    private void refresh(User user, SmartlifeCredentialsDTO dto) throws NoSmartlifeAccountAuthenticatedException {
        try {
            TokenResponse tokenResponse = new RefreshTokenRequest(dto.getRefreshToken()).executeAndGet();
            dto.setAccessToken(tokenResponse.getAccessToken());
            dto.setRefreshToken(tokenResponse.getRefreshToken());
            dto.setExpireTime(tokenResponse.getExpireTime());
            repo.commit(dto);
        } catch (Exception e) {
            cachedCredentials.remove(user.getId().toString());
            repo.deleteByUser(user.getId());
            throw new NoSmartlifeAccountAuthenticatedException();
        }
    }

    public List<TuyaDeviceDTO> getDevicesByUser(User user) throws NoSmartlifeAccountAuthenticatedException, IOException {
        return new GetDevicesRequest(getByUser(user)).executeAndGet();
    }

    public List<TuyaHomeDTO> getHomesByUser(User user) throws NoSmartlifeAccountAuthenticatedException, IOException {
        return new GetHomesRequest(getByUser(user)).executeAndGet();
    }

    public List<TuyaSceneDTO> getScenesByUserAndHomeId(User user, long homeId) throws NoSmartlifeAccountAuthenticatedException, IOException {
        return new GetScenesByHomeRequest(getByUser(user), homeId).executeAndGet();
    }

    public PanelResultDTO triggerSceneByUserAndHome(User user, long homeId, String sceneId) throws NoSmartlifeAccountAuthenticatedException, IOException {
        return new PanelResultDTO(new TriggerSceneRequest(getByUser(user), sceneId, homeId).executeAndGet().getSuccess() ? PanelResultType.OK : PanelResultType.ERROR);
    }
}
