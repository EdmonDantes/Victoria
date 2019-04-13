package ru.teamname.projectname.repositories.answerRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entites.Answer;

@Service
@Repository
@Transactional
public interface AnswerRepository extends CrudRepository<Answer, Integer> {

    @Query("select a from Answer a where a.answer = :answer")
    Answer findAnswerByString(@Param("answer") String answer);

    @Query("update Answer a set a.id = :id where a.answer = :answer")
    @Modifying
    void setAnserString(@Param("id") Integer id, @Param("answer") String answer);
}
