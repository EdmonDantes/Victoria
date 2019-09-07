package ru.teamname.projectname.controller.api.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.account.AccountToken;
import ru.teamname.projectname.repository.account.AccountTokenRepository;
import ru.teamname.projectname.service.AccountService;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountTokenRepository accountTokenRepository;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public @ResponseBody Account getAccount(@RequestParam Integer id, @RequestParam String token) {
        AccountToken aToken = accountTokenRepository.getByToken(token);
        if (aToken != null && aToken.getId() != null)
            return accountService.getAccount(id);
        return new Account();
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Account registerAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @RequestMapping(path = "/token/create", method = RequestMethod.GET)
    public @ResponseBody AccountToken createToken(@RequestParam String login, @RequestParam String password) {
        return accountService.addAccountToken(login, password);
    }

    @RequestMapping(path = "/fromToken", method = RequestMethod.GET)
    public @ResponseBody Account fromToken(@RequestParam String token) {
        return accountService.getAccountFromToken(token);
    }

}
