package ru.teamname.projectname.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.game.Lobby;
import ru.teamname.projectname.repository.account.AccountRepository;
import ru.teamname.projectname.repository.game.GameRepository;
import ru.teamname.projectname.repository.game.LobbyRepository;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LobbyRepository lobbyRepository;



    @Autowired
    private AccountRepository accountRepository;

    public Lobby getLobby(Integer id) {
        if (id != null && id > 0 && lobbyRepository != null) {
            Optional<Lobby> lobbyOptional = lobbyRepository.findById(id);
            if (lobbyOptional.isPresent())
                return lobbyOptional.get();
        }
        return new Lobby();
    }

    public Lobby addLobby(Lobby lobby, Account account) {
        if (lobby != null && (lobby.getId() == null || lobby.getId() < 1) && lobbyRepository != null) {
            if (account != null && account.getId() != null && account.getId() > 0 && account.getLobby() == null) {
                lobby.setAdmin(account);
                lobbyRepository.save(lobby);
                //accountRepository.updateLobby(account.getId(), lobby);
            }

        }
        return lobby == null ? new Lobby() : lobby;
    }

    public boolean addPlayer(Integer id, Account account) {
        //lobbyRepository.addPlayer(account.getId(), id);
        return id != null && id > 0 && account != null && account.getId() != null && account.getId() > 0 && lobbyRepository != null;
    }
}
