package ru.teamname.projectname;

import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.game.Lobby;
import ru.teamname.projectname.repository.account.AccountRepository;
import ru.teamname.projectname.repository.game.LobbyRepository;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {

    @Resource
    private AccountRepository accountRepository;

    @Resource
    private LobbyRepository lobbyRepository;

    @org.junit.Test
    @Transactional
    public void test1(){
        Account account = new Account();
        account.setLogin("Test");
        account.setPassword("test");
        account.setCountGame(52);
        account.setGamePoints(85);

        accountRepository.save(account);
        accountRepository.flush();

        Lobby lobby = new Lobby();
        lobby.setName("TestLobby");
        lobby.setAdmin(account);
        //lobby.setPlayers(Collections.singleton(account));
        lobbyRepository.save(lobby);
        lobbyRepository.flush();

        lobbyRepository.addPlayerToLobby(lobby.getId(), account.getId());
        lobbyRepository.flush();

        //accountRepository.updateLobby(account.getId(), lobby);
        //accountRepository.flush();
    }

    @org.junit.Test
    @Transactional
    public void test2(){
        List<Lobby> lobbySet = lobbyRepository.findAll();
        Lobby lobby = lobbySet.get(0);
        Hibernate.initialize(lobby.getPlayers());
        assert lobby.getPlayers().size() > 0;

        List<Account> accounts = accountRepository.findAll();
        Account account = accounts.get(0);
        if (!Hibernate.isInitialized(account.getLobby()))
            Hibernate.initialize(account.getLobby());
        assert account.getLobby() != null;
    }

    @org.junit.Test
    @Transactional
    public void test3(){}
}
