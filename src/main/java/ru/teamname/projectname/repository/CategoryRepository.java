package ru.teamname.projectname.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.Category;

@Service
@Repository
@Transactional
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query(nativeQuery = true, value = "INSERT INTO category_questions(category_id, questions_id) VALUES(:id, :question_id) ON DUPLICATE KEY UPDATE category_id = :id, questions_id = :question_id")
    @Modifying
    void addQuestion(@Param("id") Integer id, @Param("question_id") Integer question_id);
}
