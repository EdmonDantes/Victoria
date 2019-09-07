package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.game.Lobby;

@Repository
public interface LobbyRepository extends JpaRepository<Lobby, Integer>{

    @Query("update Lobby l set l.players = :account where l.id = ")
    public void addPlayer(@Param("account") Account account, @Param("id") Integer id);
}
