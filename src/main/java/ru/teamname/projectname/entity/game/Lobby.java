package ru.teamname.projectname.entity.game;


import lombok.Data;
import ru.teamname.projectname.entity.account.Account;

import javax.persistence.*;

@Entity
@Data
public class Lobby {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer count;

    @ManyToOne
    private Account admin;

    private String local;

    private Integer maxPlayers;

    private Integer maxCategories;

    private Integer maxQuestion;

    private Integer gamesId;
}
