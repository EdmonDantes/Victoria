package ru.teamname.projectname.repository.localization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.localization.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    @Query(nativeQuery = true, value = "INSERT INTO `currency_name`(`currency_id`, `name_id`) VALUES (:currency, :name) ON DUPLICATE KEY UPDATE `currency_id` = :currency, `name_id` = :name")
    @Modifying
    public void addName(@Param("currency") Integer currency, @Param("name") Integer name);

    @Query(nativeQuery = true, value = "DELETE FROM `currency_name` WHERE `currency_id` = :currency AND `name_id` = :name")
    @Modifying
    public void deleteName(@Param("currency") Integer currency, @Param("name") Integer name);

}
