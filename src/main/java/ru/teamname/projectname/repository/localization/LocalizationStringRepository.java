package ru.teamname.projectname.repository.localization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.localization.LocalizationString;

@Repository
public interface LocalizationStringRepository extends JpaRepository<LocalizationString, Integer> {}
