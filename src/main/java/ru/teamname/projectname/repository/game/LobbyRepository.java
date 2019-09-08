package ru.teamname.projectname.repository.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.game.Game;
import ru.teamname.projectname.entity.game.Lobby;

@Repository
public interface LobbyRepository extends JpaRepository<Lobby, Integer>{

    @Query(nativeQuery = true, value = "INSERT INTO `lobby_players`(`lobby_id`, `players_id`) value(:lobby, :player) ON DUPLICATE KEY UPDATE lobby_id = :lobby, players_id = :player")
    @Modifying
    void addPlayerToLobby(@Param("lobby") Integer lobby, @Param("player") Integer player);

    @Query(nativeQuery = true, value = "DELETE FROM `lobby_players` WHERE `lobby_id` = :lobby AND `players_id` = :player")
    @Modifying
    void deletePlayerFromLobby(@Param("lobby") Integer lobby, @Param("player") Integer player);

    @Query(nativeQuery = true, value = "INSERT INTO `lobby_packs`(`lobby_id`, `packs_id`) value(:lobby, :pack) ON DUPLICATE KEY UPDATE lobby_id = :lobby, packs_id = :pack")
    @Modifying
    void addPackToLobby(@Param("lobby") Integer lobby, @Param("pack") Integer pack);

    @Query(nativeQuery = true, value = "DELETE FROM `lobby_packs` WHERE `lobby_id` = :lobby AND `packs_id` = :pack")
    @Modifying
    void deletePackToLobby(@Param("lobby") Integer lobby, @Param("pack") Integer pack);

    @Query("update Lobby lobby set lobby.name = :name where lobby.id = :id")
    @Modifying
    void updateName(@Param("id") Integer id, @Param("name") String name);

    @Query("update Lobby lobby set lobby.admin = :admin where lobby.id = :id")
    @Modifying
    void updateAdmin(@Param("id") Integer id, @Param("admin") Account admin);

    @Query("update Lobby lobby set lobby.locale = :locale where lobby.id = :id")
    @Modifying
    void updateLocale(@Param("id") Integer id, @Param("locale") String locale);

    @Query("update Lobby lobby set lobby.maxPlayers = :maxPlayers where lobby.id = :id")
    @Modifying
    void updateMaxPlayers(@Param("id") Integer id, @Param("maxPlayers") Integer maxPlayers);

    @Query("update Lobby lobby set lobby.maxCategories = :maxCategories where lobby.id = :id")
    @Modifying
    void updateMaxCategories(@Param("id") Integer id, @Param("maxCategories") Integer maxCategories);

    @Query("update Lobby lobby set lobby.maxQuestion = :maxQuestion where lobby.id = :id")
    @Modifying
    void updateMaxQuestion(@Param("id") Integer id, @Param("maxQuestion") Integer maxQuestion);

    @Query("update Lobby lobby set lobby.game = :game where lobby.id = :id")
    @Modifying
    void updateGame(@Param("id") Integer id, @Param("game") Game game);
}
