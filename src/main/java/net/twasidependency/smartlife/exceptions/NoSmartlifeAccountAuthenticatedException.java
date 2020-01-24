package net.twasidependency.smartlife.exceptions;

public class NoSmartlifeAccountAuthenticatedException extends SmartlifeIntegrationException {

    public NoSmartlifeAccountAuthenticatedException() {
    }

    public NoSmartlifeAccountAuthenticatedException(String message) {
        super(message);
    }
}
