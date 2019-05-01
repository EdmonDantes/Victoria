package ru.teamname.projectname.entity.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.thymeleaf.standard.expression.Each;

import javax.persistence.*;

@Entity
@Data
public class PlayerStatus {

    public PlayerStatus(){}

    public PlayerStatus(Player player, Integer status, Lobby lobby){
        this.playerId = player;
        this.status = status;
        this.lobbyId = lobby;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "playerId", unique = true, nullable = false)
    private Player playerId;

    /**
     * -1 - Leave from lobby
     * 0 - Not ready
     * 1 - Ready
     * 2 - In game // TODO: create and using this value
     */
    @Column(nullable = false)
    private Integer status = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lobbyId")
    @JsonIgnore
    private Lobby lobbyId;

    //TODO: Add gameId for gaming player
}
