package ru.teamname.projectname.repository.packs;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.teamname.projectname.entity.packs.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {}
