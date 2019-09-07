package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.game.Lobby;

@Repository
public interface LobbyRepository extends JpaRepository<Lobby, Integer>{}
