package ru.teamname.projectname.entity.game;

import lombok.Data;
import ru.teamname.projectname.entity.packs.Question;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class GameQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer x = 0;

    @Column(nullable = false)
    private Integer y = 0;

    @OneToOne
    private Question question;

    @Column(nullable = false)
    private Boolean played = false;
}
