package net.twasiplugin.smartlife.services;

import net.twasi.core.database.models.User;
import net.twasi.core.services.IService;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.smartlife.api.graphql.models.DeviceDTO;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsDTO;
import net.twasiplugin.smartlife.database.SmartlifeCredentialsRepo;
import net.twasiplugin.smartlife.exceptions.NoSmartlifeAccountAuthenticatedException;
import net.twasiplugin.smartlife.remote.requests.GetDevicesRequest;
import net.twasiplugin.smartlife.remote.requests.RefreshTokenRequest;
import net.twasiplugin.smartlife.remote.responses.TokenResponse;

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

    public List<DeviceDTO> getDevicesByUser(User user) throws NoSmartlifeAccountAuthenticatedException, IOException {
        return new GetDevicesRequest(getByUser(user)).executeAndGet();
    }
}
