package ru.teamname.projectname.entity.account;

import lombok.Data;
import ru.teamname.projectname.entity.game.Lobby;

import javax.persistence.*;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String login;

    private String password;

    private Integer gamePoints;

    private Integer countGame;

    private Long registerDateTime;

    private Long lastReciveRequestDateTime;

    @ManyToOne
    @Column(nullable = true)
    private Lobby lobby;
}
