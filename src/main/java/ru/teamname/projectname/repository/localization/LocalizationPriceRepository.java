package ru.teamname.projectname.repository.localization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.localization.LocalizationPrice;

@Repository
public interface LocalizationPriceRepository extends JpaRepository<LocalizationPrice, Integer> {}
