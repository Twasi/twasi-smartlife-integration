package net.twasiplugin.smartlife.api.graphql.models.sequences;

import net.twasi.core.database.models.User;
import net.twasi.core.graphql.model.GraphQLPagination;
import net.twasi.core.graphql.model.PanelResultDTO;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.smartlife.database.sequences.SmarthomeSequenceRepo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.stream.Collectors;

public class SceneSequencesDTO {

    private static SmarthomeSequenceRepo repo = DataService.get().get(SmarthomeSequenceRepo.class);
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
        throw new NotImplementedException(); // TODO implement
    }

    public PanelResultDTO delete(String id) {
        throw new NotImplementedException(); // TODO implement
    }

    public SceneSequenceDTO create(SceneSequenceInputDTO newSequence) {
        throw new NotImplementedException(); // TODO implement
    }

    public SceneSequenceDTO update(String id, SceneSequenceInputDTO update) {
        throw new NotImplementedException(); // TODO implement
    }

}
