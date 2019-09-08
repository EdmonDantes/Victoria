package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.game.PlayerStatus;

@Repository
public interface PlayerStatusRepository extends JpaRepository<PlayerStatus, Integer> {

    @Query("update PlayerStatus ps set ps.scope = :scope where ps.id = :id")
    @Modifying
    public void updateScope(@Param("id") Integer id, @Param("scope") Integer scope);

}
