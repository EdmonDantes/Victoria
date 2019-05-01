package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.game.Player;

import java.util.Optional;

@Repository
@Transactional
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query("select player from Player player where player.name = :name")
    Optional<Player> findByName(@Param("name") String name);

    @Query("select player from Player player where player.email = :email")
    Optional<Player> findByEmail(@Param("email") String email);

    @Query("select player from Player player where player.name = :input or player.email = :input")
    Optional<Player> findByNameOrEmail(@Param("input") String nameOrEmail);

    @Query("update Player player set player.name = :name where player.id = :id")
    @Modifying
    void setName(@Param("id") Integer id, @Param("name") String name);

    @Query("update Player player set player.password = :password where player.id = :id")
    @Modifying
    void setNewPassword(@Param("id") Integer id, @Param("password") String password);

    @Query("update Player player set player.email = :email where player.id = :id")
    @Modifying
    void setEmail(@Param("id") Integer id, @Param("email") String email);

    @Query("update Player player set player.countOfGamePoints = player.countOfGamePoints + :points where player.id = :id")
    @Modifying
    void addGamePoints(@Param("id") Integer id, @Param("points") Integer points);

    @Query("update Player player set player.countGames = player.countGames + 1 where player.id = :id")
    @Modifying
    void addOneGame(@Param("id") Integer id);

    @Query(value = "INSERT INTO players_buying_packs(player_id, buying_packs_id) VALUES(:id, :packId) ON DUPLICATE KEY UPDATE player_id = :id, buying_packs_id = :packId", nativeQuery = true)
    @Modifying
    void addBuyingPack(@Param("id") Integer id, @Param("packId") Integer packId);

    @Query(value = "INSERT INTO player_see_question(player_id, see_question_id) VALUES(:id, :questionId) ON DUPLICATE KEY UPDATE player_id = :id, see_question_id = :questionId", nativeQuery = true)
    @Modifying
    void addSeeQuestion(@Param("id") Integer id, @Param("questionId") Integer questionId);
}
