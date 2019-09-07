package ru.teamname.projectname.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.game.Lobby;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    public Account getAccountByLoginAndPassword(String login, String password);

    @Query("update Account account set account.lobby = :lobby where account.id = :id")
    public void updateAccountByLobby(@Param("id") Integer id, @Param("lobby") Lobby lobby);

}
