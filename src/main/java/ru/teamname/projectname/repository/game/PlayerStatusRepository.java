package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.game.PlayerStatus;

@Repository
public interface PlayerStatusRepository extends JpaRepository<PlayerStatus, Integer> {}
