package ru.teamname.projectname.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.account.AccountToken;

import java.util.List;

@Repository
public interface AccountTokenRepository extends JpaRepository<AccountToken, Integer> {

    public AccountToken getByToken(String token);

    public void deleteByToken(String token);

    public void deleteByAccount(Account account);

    public List<AccountToken> getAllByAccount(Account account);

}
