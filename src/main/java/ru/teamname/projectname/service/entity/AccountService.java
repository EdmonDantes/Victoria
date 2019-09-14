package ru.teamname.projectname.service.entity;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.account.AccountToken;
import ru.teamname.projectname.repository.account.AccountRepository;
import ru.teamname.projectname.repository.account.AccountTokenRepository;

import java.util.ArrayList;
import java.util.List;
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

    public List<Account> getAccounts(){
        if (accountRepository != null)
            return accountRepository.findAll();
        return new ArrayList<>();
    }

    public boolean deleteAccount(Integer id) {
        if (id != null && id > 0 && accountRepository != null) {
            try {
                accountRepository.deleteById(id);
                return true;
            }catch (Exception ignore){}
        }
        return false;
    }

    public Account addAccount(Account account) {
        if (account != null && (account.getId() == null || account.getId() < 0) && accountRepository != null) {
            try {
                return accountRepository.save(account);
            } catch (Exception ignore){}
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

    public AccountToken addAccountToken(Account account) {
        if (account != null && account.getId() != null && account.getId() > 0 && accountTokenRepository != null) {
                AccountToken accountToken = new AccountToken();
                accountToken.setAccount(account);
                accountToken.setToken(RandomString.make(120));
                try {
                    return accountTokenRepository.save(accountToken);
                } catch (Exception ignore){}
        }
        return new AccountToken();
    }

    public AccountToken addAccountToken(String login, String password) {
        if (login != null && password != null && accountRepository != null) {
            Optional<Account> accountOptional = accountRepository.getAccountByLoginAndPassword(login, password);
            if (accountOptional.isPresent())
                return this.addAccountToken(accountOptional.get());
        }
        return new AccountToken();
    }

    public boolean deleteAccountToken(Integer id) {
        if (id != null && id > 0 && accountTokenRepository != null) {
            try {
                accountTokenRepository.deleteById(id);
                return true;
            } catch (Exception ignore){}
        }
        return false;
    }

    public boolean deleteAccountToken(String token) {
        if (token != null && accountTokenRepository != null) {
            try {
                accountTokenRepository.deleteByToken(token);
                return true;
            } catch (Exception ignore){}
        }
        return false;
    }

    public boolean deleteAccountToken(Account account) {
        if (account != null && account.getId() != null && account.getId() > 0 && accountTokenRepository != null) {
            try {
                accountTokenRepository.deleteByAccount(account);
                return true;
            } catch (Exception ignore){}
        }
        return false;
    }

    public List<AccountToken> getAccountsTokens(Account account) {
        if (account != null && account.getId() != null && account.getId() > 0 && accountTokenRepository != null) {
            return accountTokenRepository.getAllByAccount(account);
        }
        return new ArrayList<>();
    }



}
