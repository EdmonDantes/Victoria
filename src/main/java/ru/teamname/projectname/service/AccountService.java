package ru.teamname.projectname.service;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.account.AccountToken;
import ru.teamname.projectname.repository.account.AccountRepository;
import ru.teamname.projectname.repository.account.AccountTokenRepository;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountTokenRepository accountTokenRepository;

    public Account getAccount(Integer id) {
        if (id != null && id > 0 && accountRepository != null) {
            Optional<Account> optionalAccount = accountRepository.findById(id);
            if (optionalAccount.isPresent())
                return optionalAccount.get();
        }
        return new Account();
    }

    public Account addAccount(Account account) {
        if (account != null && (account.getId() == null || account.getId() < 0) && accountRepository != null) {
            return accountRepository.save(account);
        }
        return account == null ? new Account() : account;
    }

    public Account getAccountFromToken(String token) {
        if (token != null && accountTokenRepository != null) {
            AccountToken accountToken = accountTokenRepository.getByToken(token);
            if (accountToken != null && accountToken.getAccount() != null)
                return accountToken.getAccount();
        }
        return new Account();
    }

    public AccountToken addAccountToken(String login, String password) {
        if (login != null && password != null && accountRepository != null) {
            Account account = accountRepository.getAccountByLoginAndPassword(login, password);
            if (account != null && account.getId() != null) {
                AccountToken accountToken = new AccountToken();
                accountToken.setAccount(account);
                accountToken.setToken(RandomString.make(120));
                return accountTokenRepository.save(accountToken);
            }
        }
        return new AccountToken();
    }

}
