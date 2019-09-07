package ru.teamname.projectname.repository.localization;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.teamname.projectname.entity.localization.LocalizationPrice;

public interface LocalizationPriceRepository extends JpaRepository<LocalizationPrice, Integer> {}
