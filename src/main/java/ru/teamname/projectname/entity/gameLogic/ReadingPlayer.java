package ru.teamname.projectname.entity.gameLogic;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class ReadingPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private Player player;

    @NotNull
    private Boolean readyPlayer;
}
