package ru.teamname.projectname.repository.packs;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.packs.Question;

@Service
@Repository
@Transactional
public interface QuestionRepository extends CrudRepository<Question, Integer> {

    @Query("select q from Question q where q.question = :question and q.price = :price")
    Question findQuestionByStringAndPrice(@Param("question") String question, @Param("price") Integer price);

    @Query("update Question q set q.question = :question where q.id = :id")
    @Modifying
    void setQuestion(@Param("id") Integer id, @Param("question") String question);

    @Query("update Question q set q.price = :price where q.id = :id")
    @Modifying
    void setPrice(@Param("id") Integer id, @Param("price") Integer price);

    @Query(nativeQuery = true, value = "INSERT INTO question_answers(question_id, answers_id) VALUES(:id, :answer_id) ON DUPLICATE KEY UPDATE question_id=:id, answers_id=:answer_id")
    @Modifying
    void addAnswer(@Param("id") Integer id, @Param("answer_id") Integer answer_id);

    @Query(value = "delete from question_answers where question_id = :id and answers_id = :answer_id", nativeQuery = true)
    @Modifying
    void deleteAnswerFromQuestion(@Param("id") Integer id, @Param("answer_id") Integer answer_id);

    @Query(value = "delete from question_answers where question_id = :id", nativeQuery = true)
    @Modifying
    void deleteAnswersFromQuestion(@Param("id") Integer id);

    @Query(value = "delete from question_answers where answers_id = :id", nativeQuery = true)
    @Modifying
    void deleteAnswerFromQuestions(@Param("id") Integer answer_id);
}
