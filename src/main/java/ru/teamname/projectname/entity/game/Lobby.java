package ru.teamname.projectname.entity.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.teamname.projectname.entity.account.Account;
import ru.teamname.projectname.entity.packs.Pack;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Lobby implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Account> players;

    @ManyToOne
    private Account admin;

    @Column(nullable = false)
    private String locale = "en-US";

    @Column(nullable = false)
    private Integer maxPlayers = 2;

    @Column(nullable = false)
    private Integer maxCategories = 4;

    @Column(nullable = false)
    private Integer maxQuestion = 4;

    @Column(nullable = false)
    private Long dateTimeCreate = System.currentTimeMillis();

    @OneToMany
    private Set<Pack> packs = new HashSet<>();

    @OneToOne
    private Game game = null;
}
