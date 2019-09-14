package ru.teamname.projectname.entity.game;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Lobby lobby;

    @OneToMany
    private Set<GameQuestion> questions = new HashSet<>();

    @OneToMany
    private Set<PlayerStatus> playerStatus = new HashSet<>();

    @Column(nullable = false)
    private Long dateTimeStart = System.currentTimeMillis();

    private Long dateTimeEnd;
}
