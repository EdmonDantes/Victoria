package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.account.AccountToken;

@Repository
public interface AccountTokenRepository extends JpaRepository<AccountToken, Integer> {}
