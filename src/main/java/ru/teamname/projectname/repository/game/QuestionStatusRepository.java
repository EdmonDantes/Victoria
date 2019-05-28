package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.game.QuestionStatus;
import ru.teamname.projectname.entity.packs.Question;

@Repository
@Transactional
public interface QuestionStatusRepository extends CrudRepository<QuestionStatus, Integer> {

    @Query("update QuestionStatus qstatus set qstatus.questionId = :questionId where qstatus.id = :id")
    @Modifying
    void updateQuestionId(@Param("id") Integer id, @Param("questionId") Question questionId);

    @Query("update QuestionStatus qstatus set qstatus.status = :status where qstatus.id = :id")
    @Modifying
    void updateStatus(@Param("id") Integer id, @Param("status") Integer status);

}
