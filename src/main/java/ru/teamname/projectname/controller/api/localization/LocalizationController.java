package ru.teamname.projectname.controller.api.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamname.projectname.entity.account.AccountToken;
import ru.teamname.projectname.entity.localization.Currency;
import ru.teamname.projectname.entity.localization.LocalizationPrice;
import ru.teamname.projectname.entity.localization.LocalizationString;
import ru.teamname.projectname.repository.account.AccountTokenRepository;
import ru.teamname.projectname.service.entity.LocalizationService;

@RestController
@RequestMapping(path = "api/localization")
public class LocalizationController {

    @Autowired
    LocalizationService localizationService;

    @Autowired
    AccountTokenRepository accountTokenRepository;

    @RequestMapping(path = "/string/get", method = RequestMethod.GET)
    public @ResponseBody LocalizationString getLString(@RequestParam Integer id, @RequestParam String token) {
        AccountToken aToken = accountTokenRepository.getByToken(token);
        if (aToken != null && aToken.getId() != null)
            return localizationService.getLString(id);
        return new LocalizationString();
    }


    //TODO: move to admin category
    @RequestMapping(path = "/string/add", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody LocalizationString addLString(@RequestBody LocalizationString lstring, @RequestParam String token) {
        AccountToken aToken = accountTokenRepository.getByToken(token);
        if (aToken != null && aToken.getId() != null)
            return localizationService.addLString(lstring);
        return  new LocalizationString();
    }

    @RequestMapping(path = "/currency/get", method = RequestMethod.GET)
    public @ResponseBody Currency getCurrency(@RequestParam Integer id, @RequestParam String token) {
        AccountToken aToken = accountTokenRepository.getByToken(token);
        if (aToken != null && aToken.getId() != null)
            return localizationService.getCurrency(id);
        return new Currency();
    }

    @RequestMapping(path = "/price/get", method = RequestMethod.GET)
    public @ResponseBody LocalizationPrice getLPrice(@RequestParam Integer id, @RequestParam String token) {
        AccountToken aToken = accountTokenRepository.getByToken(token);
        if (aToken != null && aToken.getId() != null)
            return localizationService.getLPrice(id);
        return new LocalizationPrice();
    }
}
