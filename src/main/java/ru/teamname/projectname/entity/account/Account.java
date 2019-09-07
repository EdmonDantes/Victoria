package ru.teamname.projectname.entity.account;

import lombok.Data;
import ru.teamname.projectname.entity.game.Lobby;
import ru.teamname.projectname.entity.packs.Question;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String login;

    private String password;

    private Integer gamePoints;

    private Integer countGame;

    private Long registerDateTime;

    private Long lastReceiveRequestDateTime;

    @ManyToMany
    private Set<Question> playedQuestions;

    @OneToOne
    private Lobby lobby;
}
