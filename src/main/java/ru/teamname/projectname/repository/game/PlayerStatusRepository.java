package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.game.Player;
import ru.teamname.projectname.entity.game.PlayerStatus;

import java.util.Optional;

@Repository
@Transactional
public interface PlayerStatusRepository extends CrudRepository<PlayerStatus, Integer> {

    @Query("select status from PlayerStatus status where status.playerId = :playerId")
    Optional<PlayerStatus> findByPlayerId(@Param("playerId") Player playerId);

    @Query("update PlayerStatus status set status.lobbyId = :lobbyId where status.id = :id")
    @Modifying
    void updateLobbyId(@Param("id") Integer id, @Param("lobbyId") Integer lobbyId);

    @Query("update PlayerStatus status set status.gameId = :gameId where status.id = :id")
    @Modifying
    void updateGameId(@Param("id") Integer id, @Param("gameId") Integer gameId);

    @Query("update PlayerStatus status set status.status = :status where status.id = :id")
    @Modifying
    void updateStatus(@Param("id") Integer id, @Param("status") Integer status);
}
