package ru.teamname.projectname.repository.packs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.packs.Pack;

@Repository
public interface PackRepository extends JpaRepository<Pack, Integer> {

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "INSERT INTO `pack_name`(`pack_id`, `name_id`) VALUES (:pack, :name) ON DUPLICATE KEY UPDATE `pack_id` = :pack, `name_id` = :name")
    @Modifying
    public void addName(@Param("pack") Integer pack, @Param("name") Integer name);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "DELETE FROM `pack_name` WHERE `pack_id` = :pack AND `name_id` = :name")
    @Modifying
    public void deleteName(@Param("pack") Integer pack, @Param("name") Integer name);

    @SuppressWarnings("all")
    @Query("update Pack p set p.price = :price where p.id = :pack")
    @Modifying
    public void updatePrice(@Param("pack") Integer pack, @Param("price") Integer price);

    @SuppressWarnings("all")
    @Query("update Pack p set p.minAge = :minAge where p.id = :pack")
    @Modifying
    public void updateMinAge(@Param("pack") Integer pack, @Param("minAge") Integer minAge);

}
