package ru.teamname.projectname.repository.packs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.packs.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {}
