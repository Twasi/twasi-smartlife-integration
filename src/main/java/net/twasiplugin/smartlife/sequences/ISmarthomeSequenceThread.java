package net.twasiplugin.smartlife.sequences;

import net.twasiplugin.smartlife.exceptions.SmartlifeIntegrationException;

public interface ISmarthomeSequenceThread {

    void onError(SmartlifeIntegrationException e);

    void onFinish();

}
