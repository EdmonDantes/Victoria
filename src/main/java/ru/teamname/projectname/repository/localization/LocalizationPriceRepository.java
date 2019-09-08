package ru.teamname.projectname.repository.localization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.localization.LocalizationPrice;

@Repository
public interface LocalizationPriceRepository extends JpaRepository<LocalizationPrice, Integer> {

    @Query("update LocalizationPrice lprice set lprice.currency = :currency where lprice.id = :lparam")
    @Modifying
    public void updateCurrency(@Param("lparam") Integer lparam, @Param("currency") Integer currency);

}
