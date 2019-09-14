package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.game.GameQuestion;

@Repository
public interface GameQuestionRepository extends JpaRepository<GameQuestion, Integer>{


    @Query("update GameQuestion q set q.played = :played where q.id = :id")
    @Modifying
    void updatePlayed(@Param("id") Integer id, @Param("played") Boolean played);

}
