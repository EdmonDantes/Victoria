package ru.teamname.projectname.controller.admin.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.account.AccountToken;
import ru.teamname.projectname.service.entity.AccountService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/account")
public class AccountAdminController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public @ResponseBody Account getAccount(@RequestParam Integer id) {
        return accountService.getAccount(id);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public @ResponseBody List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody Boolean deleteAccount(@RequestParam Integer id) {
        return accountService.deleteAccount(id);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Account createAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @RequestMapping(path = "/token/create", method = RequestMethod.GET)
    public @ResponseBody AccountToken createToken(@RequestParam Integer id){
        return accountService.addAccountToken(accountService.getAccount(id));
    }

    @RequestMapping(path = "/token/delete", method = RequestMethod.DELETE)
    public @ResponseBody Boolean deleteToken(@RequestParam Integer id) {
        return accountService.deleteAccountToken(id);
    }

//    @RequestMapping(path = "/token/delete", method = RequestMethod.DELETE)
//    public @ResponseBody Boolean deleteToken(@RequestParam String token) {
//        return accountService.deleteAccountToken(token);
//    }
//
//    @RequestMapping(path = "/token/delete", method = RequestMethod.POST, consumes = "application/json")
//    public @ResponseBody Boolean deleteToken(@RequestBody Account account) {
//        return accountService.deleteAccountToken(account);
//    }

    @RequestMapping(path = "/token/get", method = RequestMethod.GET)
    public @ResponseBody List<AccountToken> getTokens(@RequestParam Integer id) {
        Account account = new Account();
        account.setId(id);
        return accountService.getAccountsTokens(account);
    }

    @RequestMapping(path = "/token/get", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<AccountToken> getTokens(@RequestParam Account account) {
        return accountService.getAccountsTokens(account);
    }
}
