package ru.teamname.projectname.repository.packs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.packs.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {}
