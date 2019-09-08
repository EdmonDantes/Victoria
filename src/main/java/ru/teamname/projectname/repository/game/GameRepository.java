package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.game.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    //TODO: create native query
    public void addGameQuestion(@Param("game") Integer game, @Param("question") Integer question);

    //TODO: create native query
    public void deleteGameQuestion(@Param("game") Integer game, @Param("question") Integer question);

    //TODO: create native query
    public void addPlayerStatus(@Param("game") Integer game, @Param("status") Integer status);

    //TODO: create native query
    public void deletePlayerStatus(@Param("game") Integer game, @Param("status") Integer status);

}
