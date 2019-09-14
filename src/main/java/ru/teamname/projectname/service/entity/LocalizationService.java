package ru.teamname.projectname.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamname.projectname.entity.localization.Currency;
import ru.teamname.projectname.entity.localization.LocalizationPrice;
import ru.teamname.projectname.entity.localization.LocalizationString;
import ru.teamname.projectname.repository.localization.CurrencyRepository;
import ru.teamname.projectname.repository.localization.LocalizationPriceRepository;
import ru.teamname.projectname.repository.localization.LocalizationStringRepository;

import java.util.Optional;

@Service
public class LocalizationService {

    @Autowired
    private LocalizationStringRepository localizationStringRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private LocalizationPriceRepository localizationPriceRepository;

    public LocalizationString getLString(Integer id) {
        if (id != null && id > 0 && localizationStringRepository != null) {
            Optional<LocalizationString> optionalLocalizationString = localizationStringRepository.findById(id);
            if (optionalLocalizationString.isPresent())
                return optionalLocalizationString.get();
        }
        return new LocalizationString();
    }

    public LocalizationString addLString(LocalizationString lstring){
        if (lstring != null && (lstring.getId() == null || lstring.getId() < 0) && localizationStringRepository != null) {
            return localizationStringRepository.save(lstring);
        }
        return lstring == null ? new LocalizationString() : lstring;
    }

    public LocalizationString[] addLStrings(LocalizationString[] lstrings) {
        if (lstrings != null && localizationStringRepository != null) {
            for (LocalizationString lstring : lstrings)
                this.addLString(lstring);
            return lstrings;
        }
        return new LocalizationString[0];
    }

    public Currency getCurrency(Integer id) {
        if (id != null && id > 0 && currencyRepository != null) {
            Optional<Currency> optionalCurrency = currencyRepository.findById(id);
            if (optionalCurrency.isPresent())
                return optionalCurrency.get();
        }
        return new Currency();
    }

    public Currency addCurrency(Currency currency) {
        if (currency != null && (currency.getId() == null || currency.getId() < 0) && currencyRepository != null) {
            this.addLStrings(currency.getName().toArray(new LocalizationString[0]));
            return currencyRepository.save(currency);
        }
        return currency == null ? new Currency() : currency;
    }

    public Currency addNameToCurrency(Currency currency, LocalizationString lstring) {
        if (currency != null && currency.getId() != null && currency.getId() > 0 && lstring != null && lstring.getId() != null && lstring.getId() > 0 && currencyRepository != null) {
            currencyRepository.addName(currency.getId(), lstring.getId());
            currency.getName().add(lstring);
        }
        return currency == null ? new Currency() : currency;
    }

    public LocalizationPrice getLPrice(Integer id) {
        if (id != null && id > 0 && localizationPriceRepository != null) {
            Optional<LocalizationPrice> optionalLocalizationPrice = localizationPriceRepository.findById(id);
            if (optionalLocalizationPrice.isPresent())
                return optionalLocalizationPrice.get();
        }
        return new LocalizationPrice();
    }

    public LocalizationPrice addLPrice(LocalizationPrice lprice) {
        if (lprice != null && (lprice.getId() == null || lprice.getId() < 0) && localizationPriceRepository != null) {
            this.addCurrency(lprice.getCurrency());
            return localizationPriceRepository.save(lprice);
        }
        return lprice == null ? new LocalizationPrice() : lprice;
    }
}
