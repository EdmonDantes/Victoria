package ru.teamname.projectname.entity.game;


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
    private Set<Account> players = new HashSet<>();

    @ManyToOne
    private Account admin;

    private String locale;

    private Integer maxPlayers;

    private Integer maxCategories;

    private Integer maxQuestion;

    private Long dateTimeCreate = System.currentTimeMillis();

    @OneToMany
    private Set<Pack> packs = new HashSet<>();

    @OneToOne
    private Game game;
}
