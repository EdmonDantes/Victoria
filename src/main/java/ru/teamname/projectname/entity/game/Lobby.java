package ru.teamname.projectname.entity.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import ru.teamname.projectname.entity.chat.Chat;
import ru.teamname.projectname.entity.packs.Pack;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "masterId", "createdDate"})
        }
)
public class Lobby {

    public Lobby(){}
    public Lobby(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private Integer maxPlayers;

    @Column(nullable = false, updatable = false)
    private Integer timeForReading;

    @Column(nullable = false, updatable = false)
    private Integer timeForAnswering;

    @Column(nullable = false, updatable = false)
    private Integer maxRounds;

    @OneToOne
    @JoinColumn(name = "masterId", nullable = false)
    @JsonProperty(value = "master")
    private Player masterId;

    @OneToMany(mappedBy = "lobbyId", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<PlayerStatus> playerStatus = new ArrayList<>();

    @ManyToMany
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Pack> usingPacks = new ArrayList<>();

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Chat chat;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean online = true;

    @Column(updatable = false)
    @CreatedDate
    @JsonIgnore
    private Date createdDate = new Date();

//    public Integer getPlayerStatus(Player player) {
//        if (player != null && player.getId() != null)
//            for (PlayerStatus status : playerStatus) {
//                if (status.getPlayer().getId() == player.getId())
//                    return status.getStatus();
//            }
//
//        return -1;
//    }
//
//    public boolean setPlayerStatus(Player player, Integer status) {
//        if (player != null && player.getId() != null && status != null)
//            for (PlayerStatus obj : playerStatus) {
//                if (obj.getPlayer().getId() == player.getId()) {
//                    obj.setStatus(status > -2 ? status : -1);
//                    return true;
//                }
//            }
//
//        return false;
//    }
//
//    public boolean addPlayer(Player player) {
//        if (player != null && player.getId() != null) {
//            for (PlayerStatus status : playerStatus) {
//                if (status.getPlayer().getId() == player.getId())
//                    return true;
//            }
//
//            if (playerStatus.size() < maxPlayers){
//                PlayerStatus status = new PlayerStatus();
//                status.setPlayer(player);
//                status.setStatus(0);
//                playerStatus.add(status);
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    public boolean leave(Player player) {
//        return setPlayerStatus(player, -1);
//    }
}
