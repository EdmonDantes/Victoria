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

    private Integer x;

    private Integer y;

    @OneToOne
    private Question question;

    private Boolean played;
}
