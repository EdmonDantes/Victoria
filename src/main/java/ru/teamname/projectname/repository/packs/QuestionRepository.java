package ru.teamname.projectname.repository.packs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.packs.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "INSERT INTO `question_question_string`(`question_id`, `question_string_id`) VALUES (:question, :questionString) ON DUPLICATE KEY UPDATE `question_id` = :question, `question_string_id` = :questionString")
    @Modifying
    public void addQuestionString(@Param("question") Integer question, @Param("questionString") Integer questionString);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "DELETE FROM `question_question_string` WHERE `question_id` = :question AND `question_string_id` = :questionString")
    @Modifying
    public void deleteQuestionString(@Param("question") Integer question, @Param("questionString") Integer questionString);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "INSERT INTO `question_answer_string`(`question_id`, `answer_string_id`) VALUES (:question, :answerString) ON DUPLICATE KEY UPDATE `question_id` = :question, `answer_string_id` = :answerString")
    @Modifying
    public void addAnswerString(@Param("question") Integer question, @Param("answerString") Integer answerString);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "DELETE FROM `question_answer_string` WHERE `question_id` = :question AND `answer_string_id` = :answerString")
    @Modifying
    public void deleteAnswerString(@Param("question") Integer question, @Param("answerString") Integer questionString);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "INSERT INTO `question_category`(`question_id`, `category_id`) VALUES (:question, :category) ON DUBLICATE KEY UPDATE `question_id` = :question, `category_id` = :category")
    @Modifying
    public void addCategory(@Param("question") Integer question, @Param("category") Integer category);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "DELETE FROM `question_category` WHERE `question_id` = :question AND `category_id` = :category")
    @Modifying
    public void deleteCategory(@Param("question") Integer question, @Param("category") Integer category);

    @Query("update Question q set q.scope = :scope where q.id = :question")
    @Modifying
    void updateScope(@Param("question") Integer question, @Param("scope") Integer scope);
}
