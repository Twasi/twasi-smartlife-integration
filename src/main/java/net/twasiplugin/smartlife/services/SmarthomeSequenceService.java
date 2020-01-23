package net.twasiplugin.smartlife.services;

import net.twasi.core.database.models.User;
import net.twasi.core.services.IService;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.smartlife.api.graphql.models.sequences.SceneSequenceDTO;
import net.twasiplugin.smartlife.database.sequences.SmarthomeSequenceRepo;
import net.twasiplugin.smartlife.exceptions.AlreadyRunningASequenceException;
import net.twasiplugin.smartlife.exceptions.SmartlifeIntegrationException;
import net.twasiplugin.smartlife.sequences.SmarthomeSequenceThread;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmarthomeSequenceService implements IService {

    private static SmarthomeSequenceRepo repo = DataService.get().get(SmarthomeSequenceRepo.class);

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

    public SceneSequenceDTO getSequenceByVariable(User user, String variable) {
        return SceneSequenceDTO.fromDb(repo.getByUserAndVariable(user, variable));
    }

    public List<String> getSceneSequenceVariables(User user){
        return repo.getVariablesByUser(user);
    }

}
