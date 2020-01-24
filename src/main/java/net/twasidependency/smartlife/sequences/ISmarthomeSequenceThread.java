package net.twasidependency.smartlife.sequences;

import net.twasidependency.smartlife.exceptions.SmartlifeIntegrationException;

public interface ISmarthomeSequenceThread {

    void onError(SmartlifeIntegrationException e);

    void onFinish();

}
