package ru.teamname.projectname.entity.gameLogic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import ru.teamname.projectname.entity.packsLogic.Pack;
import ru.teamname.projectname.entity.packsLogic.Question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Player implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true, length = 80)
    private String username;

    @NotNull
    @Column(length = 80)
    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String email;

    @NotNull
    private Integer countOfGamePoints = 0;

    @NotNull
    private Integer countGames = 0;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Pack> buyingPacks;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Question> seeQuestion;

    @NotNull
    @Column(updatable = false)
    @CreatedDate
    private Date registerTime;
}
