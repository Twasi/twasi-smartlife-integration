package net.twasiplugin.smartlife.sequences;

import net.twasi.core.database.models.User;
import net.twasiplugin.smartlife.SmartlifeIntegration;
import net.twasiplugin.smartlife.api.graphql.models.sequences.SceneSequenceDTO;
import net.twasiplugin.smartlife.api.graphql.models.sequences.SceneSequenceStepDTO;
import net.twasiplugin.smartlife.exceptions.SmartlifeIntegrationException;
import net.twasiplugin.smartlife.services.SmartlifeIntegrationService;

import java.util.concurrent.TimeUnit;

public abstract class SmarthomeSequenceThread extends Thread implements ISmarthomeSequenceThread {

    private static SmartlifeIntegrationService service = SmartlifeIntegrationService.get();
    private final SceneSequenceDTO sceneSequence;
    private final User user;
    private boolean abort = false;

    public SmarthomeSequenceThread(SceneSequenceDTO sceneSequence, User user) {
        this.sceneSequence = sceneSequence;
        this.user = user;
        this.setDaemon(true);
        this.start();
    }

    public void abort() {
        this.abort = true;
        onFinish();
    }

    @Override
    public void run() {
        try {
            for (SceneSequenceStepDTO step : sceneSequence.getSteps()) {
                TimeUnit.MILLISECONDS.sleep(step.getMsDelay());
                if (abort) return;
                service.triggerSceneByUserAndHome(user, step.getHomeId(), step.getSceneId());
            }
        } catch (Exception e) {
            if (e instanceof SmartlifeIntegrationException)
                onError((SmartlifeIntegrationException) e);
            else
                SmartlifeIntegration.LOGGER.error("Error while running SmarthomeSequence for User " + user.getTwitchAccount().getUserName(), e);
        }
        onFinish();
    }
}
