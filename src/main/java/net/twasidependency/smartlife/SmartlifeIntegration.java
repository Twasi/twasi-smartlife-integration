package net.twasidependency.smartlife;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import net.twasi.core.api.oauth.OAuthIntegrationController;
import net.twasi.core.plugin.TwasiDependency;
import net.twasi.core.services.ServiceRegistry;
import net.twasidependency.smartlife.api.oauth.OAuthController;
import net.twasidependency.smartlife.api.graphql.SmartlifeResolver;
import net.twasidependency.smartlife.services.SmarthomeSequenceService;
import net.twasidependency.smartlife.services.SmartlifeIntegrationService;
import org.apache.log4j.Logger;

public class SmartlifeIntegration extends TwasiDependency<SmartlifeIntegrationConfig> {

    public static SmartlifeIntegrationConfig CONFIG;
    public static Logger LOGGER;

    @Override
    public void onActivate() {
        CONFIG = getConfiguration();
        LOGGER = getLogger();
        ServiceRegistry.register(new SmartlifeIntegrationService());
        ServiceRegistry.register(new SmarthomeSequenceService());
        ServiceRegistry.get(OAuthIntegrationController.class).registerOauthIntegrationHandler(new OAuthController());
    }

    @Override
    public GraphQLQueryResolver getGraphQLResolver() {
        return new SmartlifeResolver();
    }
}
