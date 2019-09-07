package ru.teamname.projectname;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.localization.Currency;
import ru.teamname.projectname.entity.localization.LocalizationPrice;
import ru.teamname.projectname.entity.localization.LocalizationString;
import ru.teamname.projectname.repository.localization.CurrencyRepository;
import ru.teamname.projectname.repository.localization.LocalizationPriceRepository;
import ru.teamname.projectname.repository.localization.LocalizationStringRepository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Resource
    private LocalizationStringRepository lstring;

    @Resource
    private CurrencyRepository cr;

    @Resource
    private LocalizationPriceRepository lpr;

    @org.junit.Test
    @Transactional
    public void test(){
        LocalizationString string1 = new LocalizationString();
        string1.setLocale(Locale.ENGLISH.toString());
        string1.setString("Dollar");
        lstring.save(string1);

        LocalizationString string2 = new LocalizationString();
        string2.setLocale("ru");
        string2.setString("Доллар");
        lstring.save(string2);

        lstring.flush();

        Currency currency = new Currency();
        currency.setName(new HashSet<>(Arrays.asList(string1, string2)));
        cr.save(currency);

        cr.flush();

        LocalizationPrice price = new LocalizationPrice();
        price.setCurrency(currency);
        price.setPrice(20);
        lpr.save(price);
        lpr.flush();

        System.out.println(price.getId());

    }
}
