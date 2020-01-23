package net.twasiplugin.smartlife.services;

import net.twasi.core.database.models.User;
import net.twasi.core.services.IService;
import net.twasiplugin.smartlife.api.graphql.models.sequences.SceneSequenceDTO;
import net.twasiplugin.smartlife.exceptions.AlreadyRunningASequenceException;
import net.twasiplugin.smartlife.exceptions.SmartlifeIntegrationException;
import net.twasiplugin.smartlife.sequences.SmarthomeSequenceThread;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class SmarthomeSequenceService implements IService {

    private Map<ObjectId, SmarthomeSequenceThread> runningSequences = new HashMap<>();

    public void runSequence(User user, SceneSequenceDTO sceneSequence) throws AlreadyRunningASequenceException {
        if (runningSequences.containsKey(user.getId())) throw new AlreadyRunningASequenceException();
        runningSequences.put(user.getId(), new SmarthomeSequenceThread(sceneSequence, user) {
            @Override
            public void onError(SmartlifeIntegrationException e) {

            }

            @Override
            public void onFinish() {
                runningSequences.remove(user.getId());
            }
        });
    }

    public void forceRunSequence(User user, SceneSequenceDTO sceneSequence) {
        if (runningSequences.containsKey(user.getId())) runningSequences.get(user.getId()).abort();
        try {
            runSequence(user, sceneSequence);
        } catch (AlreadyRunningASequenceException ignored) {
            // Will never be thrown since we aborted the running sequence
        }
    }

}
