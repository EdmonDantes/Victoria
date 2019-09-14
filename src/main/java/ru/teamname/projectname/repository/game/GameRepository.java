package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.game.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query(nativeQuery = true, value = "INSERT INTO `game_questions`(`game_id`, `question_id`) VALUES (:game, :question) ON DUPLICATE KEY UPDATE `game_id` = :game, `question_id` = :question")
    @Modifying
    void addGameQuestion(@Param("game") Integer game, @Param("question") Integer question);

    @Query(nativeQuery = true, value = "DELETE FROM `game_questions` WHERE `game_id` = :game AND `question_id` = :question")
    @Modifying
    void deleteGameQuestion(@Param("game") Integer game, @Param("question") Integer question);

    @Query(nativeQuery = true, value = " INSERT INTO `game_player_status` (`game_id`, `player_status_id`)VALUES (:game, :status) ON DUPLICATE KEY UPDATE `game_id`=:game,`player_status_id = :status`")
    @Modifying
    void addPlayerStatus(@Param("game") Integer game, @Param("status") Integer status);

    @Query(nativeQuery = true, value = "DELETE FROM `game_player_status` WHERE `game_id` = :game AND `player_status_id` = :status")
    @Modifying
    void deletePlayerStatus(@Param("game") Integer game, @Param("status") Integer status);

    @Query("update Game game set game.lobby = :lobby where game.id = :game")
    @Modifying
    void updateLobby(@Param("game") Integer game, @Param("lobby") Integer lobby);

    @Query("update Game game set game.dateTimeStart = :dateTimeStart where game.id = :game")
    @Modifying
    void updateDateTimeStart(@Param("game") Integer game, @Param("dateTimeStart") Long dateTimeStart);

    @Query("update Game game set game.dateTimeEnd = :dateTimeEnd where game.id = :game")
    @Modifying
    void updateDateTimeEnd(@Param("game") Integer game, @Param("dateTimeEnd") Long dateTimeEnd);


}

