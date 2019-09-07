package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.game.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {}
