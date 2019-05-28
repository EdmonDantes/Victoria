package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.game.Lobby;

@Repository
@Transactional
public interface LobbyRepository extends CrudRepository<Lobby, Integer> {

    @Query("select lobby from Lobby lobby where lobby.online = true and lobby.name like :name")
    Iterable<Lobby> findByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "INSERT INTO lobby_player_status(lobby_id, player_status_id) VALUES (:id, :playerStatusId) ON DUPLICATE KEY UPDATE lobby_id = :id, player_status_id = :playerStatusId")
    @Modifying
    void addPlayerStatus(@Param("id") Integer lobbyId, @Param("playerStatusId") Integer playerStatusId);

    @Query("update Lobby lobby set lobby.online = false where lobby.id = :id")
    @Modifying
    void setOffline(@Param("id") Integer id);

}
