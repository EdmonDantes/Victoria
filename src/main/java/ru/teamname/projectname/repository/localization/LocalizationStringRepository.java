package ru.teamname.projectname.repository.localization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.localization.LocalizationString;

@Repository
public interface LocalizationStringRepository extends JpaRepository<LocalizationString, Integer> {

    @Query("update LocalizationString lstring set lstring.locale = :locale where  lstring.id = :lstring")
    @Modifying
    public void updateLocale(@Param("lstring") Integer lstring, @Param("locale") String locale);

}
