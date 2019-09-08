package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.game.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    //TODO: create native query
    // game_questions
    // game_id
    // question_id
    @Query(nativeQuery = true, value = "INSERT INTO `game_questions`(`game_id`, `question_id`) VALUES (:game, :question) ON DUPLICATE KEY UPDATE `game_id` = :game, `question_id` = :question")
    @Modifying
    public void addGameQuestion(@Param("game") Integer game, @Param("question") Integer question);

    //TODO: create native query
    @Query(nativeQuery = true, value = "DELETE FROM `game_questions` WHERE `game_id` = :game AND `question_id` = :question")
    public void deleteGameQuestion(@Param("game") Integer game, @Param("question") Integer question);

    //TODO: create native query
    // game_player_status
    // game_id
    // player_status_id
    public void addPlayerStatus(@Param("game") Integer game, @Param("status") Integer status);

    //TODO: create native query
    public void deletePlayerStatus(@Param("game") Integer game, @Param("status") Integer status);

}
