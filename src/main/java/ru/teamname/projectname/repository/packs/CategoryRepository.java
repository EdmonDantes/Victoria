package ru.teamname.projectname.repository.packs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.packs.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "INSERT INTO `category_name`(`category_id`, `name_id`) VALUES (:category, :name) ON DUPLICATE KEY UPDATE `category_id` = :category, `name_id` = :name")
    @Modifying
    void addName(@Param("category") Integer category, @Param("name") Integer name);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "DELETE FROM `category_name` WHERE `category_name` = :category AND `name_id` = :name")
    @Modifying
    void deleteName(@Param("category") Integer category, @Param("name") Integer name);

}
