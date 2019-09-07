package ru.teamname.projectname.repository.localization;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.teamname.projectname.entity.localization.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {}
