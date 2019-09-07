package ru.teamname.projectname.repository.packs;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.teamname.projectname.entity.packs.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}
