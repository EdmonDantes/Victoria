package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {}
