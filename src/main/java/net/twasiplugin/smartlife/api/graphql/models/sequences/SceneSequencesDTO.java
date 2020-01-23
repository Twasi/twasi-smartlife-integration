package net.twasiplugin.smartlife.api.graphql.models.sequences;

import net.twasi.core.database.models.User;
import net.twasi.core.graphql.TwasiGraphQLHandledException;
import net.twasi.core.graphql.model.GraphQLPagination;
import net.twasi.core.graphql.model.PanelResultDTO;
import net.twasi.core.graphql.model.PanelResultDTO.PanelResultType;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.smartlife.database.sequences.SmarthomeSequenceDTO;
import net.twasiplugin.smartlife.database.sequences.SmarthomeSequenceRepo;
import net.twasiplugin.smartlife.services.SmarthomeSequenceService;
import org.bson.types.ObjectId;

import java.util.stream.Collectors;

public class SceneSequencesDTO {

    private static SmarthomeSequenceRepo repo = DataService.get().get(SmarthomeSequenceRepo.class);
    private static SmarthomeSequenceService service = ServiceRegistry.get(SmarthomeSequenceService.class);
    private User user;

    public SceneSequencesDTO(User user) {
        this.user = user;
    }

    public GraphQLPagination<SceneSequenceDTO> getList() {
        return new GraphQLPagination<>(
                () -> repo.countByUser(user),
                i -> repo.getByUserPaginated(i, user).stream().map(SceneSequenceDTO::fromDb).collect(Collectors.toList())
        );
    }

    public PanelResultDTO play(String id) {
        try {
            SmarthomeSequenceDTO byUserAndId = repo.getByUserAndId(user, new ObjectId(id));
            SceneSequenceDTO dto = SceneSequenceDTO.fromDb(byUserAndId);
            service.forceRunSequence(user, dto);
            return new PanelResultDTO(PanelResultType.OK);
        } catch (Exception e) {
            return new PanelResultDTO(PanelResultType.ERROR);
        }
    }

    public PanelResultDTO delete(String id) {
        return new PanelResultDTO(repo.deleteByUserAndId(user, id) ? PanelResultType.OK : PanelResultType.ERROR);
    }

    public SceneSequenceDTO create(SceneSequenceInputDTO newSequence) {
        if (newSequence.getVariable() != null && repo.getVariablesByUser(user).contains(newSequence.getVariable().toLowerCase()))
            throw new TwasiGraphQLHandledException("A sequence with this variable name already exists.", "SEQUENCE_VARIABLE_NAME_TAKEN");
        return SceneSequenceDTO.fromDb(repo.addAndReturn(SmarthomeSequenceDTO.fromInput(user, newSequence)));
    }

    public SceneSequenceDTO update(String id, SceneSequenceInputDTO update) {
        try {
            SmarthomeSequenceDTO dto = repo.getByUserAndId(user, new ObjectId(id));
            dto.applyUpdateFromInput(update);
            return SceneSequenceDTO.fromDb(repo.commitAndReturn(dto));
        } catch (NullPointerException e) {
            throw new TwasiGraphQLHandledException("A sequence with this id does not exist.", "SEQUENCE_ID_INVALID");
        }
    }

}
