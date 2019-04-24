package ru.teamname.projectname.entity.gameLogic;

import lombok.Data;
import org.springframework.data.annotation.Reference;
import ru.teamname.projectname.entity.packsLogic.Pack;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
public class Lobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(unique = true)
    private Player master;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    private Set<ReadingPlayer> players;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Pack> usingPacks;

    @NotNull
    private boolean online;
}
