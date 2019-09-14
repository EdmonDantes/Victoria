package ru.teamname.projectname.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.game.Lobby;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> getAccountByLoginAndPassword(String login, String password);

    Optional<Account> getByLogin(String login);

    List<Account> getAllByGamePoints(Integer gamePoints);

    List<Account> getAllByCountGame(Integer countGame);

    List<Account> getAllByLobby(Lobby lobby);

    @Query("update Account a set a.lobby = :lobby where a.id = :id")
    @Modifying
    void updateLobby(@Param("id") Integer id, @Param("lobby") Lobby lobby);

    @Query("update Account a set a.password = :password where a.id = :id")
    @Modifying
    void updatePassword(@Param("id") Integer id, @Param("password") String password);

    @Query("update Account a set a.gamePoints = :gamePoints where a.id = :id")
    @Modifying
    void updateGamePoints(@Param("id") Integer id, @Param("gamePoints") Integer gamePoints);

    @Query("update Account a set a.countGame = :countGame where a.id = :id")
    @Modifying
    void updateCountGame(@Param("id") Integer id, @Param("countGame") Integer countGame);

    @Query("update Account a set a.lastReceiveRequestDateTime = :lrrdt where a.id = :id")
    @Modifying
    void updateLastReceiveRequestDateTime(@Param("id") Integer id, @Param("lrrdt") Integer lrrdt);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "INSERT INTO `account_played_question`(`account_id`, `played_questions_id`) VALUES (:account, :question) ON DUPLICATE KEY UPDATE `account_id` = :account, `played_question_id` = :question")
    @Modifying
    void addPlayedQuestion(@Param("account") Integer account, @Param("question") Integer question);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "DELETE FROM `account_played_question` WHERE `account_id` = :account AND `playe_question_id` = :question")
    @Modifying
    void deletePlayedQuestion(@Param("account") Integer account, @Param("question") Integer question);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "INSERT INTO `account_buy_packs`(`account_id`, `buy_packs_id`) VALUES (:account, :pack) ON DUPLICATE KEY UPDATE `account_id` = :account, `buy_packs_id` = :question")
    @Modifying
    void addBuyPack(@Param("account") Integer account, @Param("pack") Integer pack);

    @SuppressWarnings("all")
    @Query(nativeQuery = true, value = "DELETE FROM `account_buy_packs` WHERE `account_id` = :account AND `buy_packs_id` = :pack")
    @Modifying
    void deleteBuyPack(@Param("account") Integer account, @Param("pack") Integer pack);

}
