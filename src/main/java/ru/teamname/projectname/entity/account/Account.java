package ru.teamname.projectname.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.teamname.projectname.entity.game.Lobby;
import ru.teamname.projectname.entity.packs.Pack;
import ru.teamname.projectname.entity.packs.Question;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private Integer gamePoints = 0;

    @Column(nullable = false)
    private Integer countGame = 0;

    @Column(nullable = false)
    private Long registerDateTime = System.currentTimeMillis();

    @Column(nullable = false)
    private Long lastReceiveRequestDateTime = System.currentTimeMillis();

    @ManyToMany
    private Set<Question> playedQuestions = new HashSet<>();

    @ManyToMany
    private Set<Pack> buyPacks = new HashSet<>();

    @OneToOne
    private Lobby lobby;
}
