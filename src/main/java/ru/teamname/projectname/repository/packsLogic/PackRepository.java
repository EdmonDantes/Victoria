package ru.teamname.projectname.repository.packsLogic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.packsLogic.Pack;

@Service
@Repository
@Transactional
public interface PackRepository extends JpaRepository<Pack, Integer> {

    @Query("select p from Pack p where p.name = :name")
    Pack getPackByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "INSERT INTO pack_categories(pack_id, categories_id) VALUES(:id, :cat_id) ON DUPLICATE KEY UPDATE pack_id = :id, categories_id = :cat_id")
    @Modifying
    void addCategory(@Param("id") Integer id, @Param("cat_id") Integer category_id);

}

