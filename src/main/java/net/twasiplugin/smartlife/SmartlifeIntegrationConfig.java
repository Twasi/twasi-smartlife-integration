package net.twasiplugin.smartlife;

import net.twasi.core.plugin.TwasiPluginConfiguration;

public class SmartlifeIntegrationConfig extends TwasiPluginConfiguration {

    public String clientId = "...";
    public String clientSecret = "...";
    public String apiUri = "https://openapi.tuyaeu.com";
    public String redirectUri = "https://api-beta.twasi.net/oauth/smartlife/callback";
    public String appSchema = "smartlife";

}
