package ru.teamname.projectname.entity.game;

import lombok.Data;
import ru.teamname.projectname.entity.account.Account;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class PlayerStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Game game;

    @OneToOne
    private Account account;

    @Column(nullable = false)
    private Integer scope = 0;
}
