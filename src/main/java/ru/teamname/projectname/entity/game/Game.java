package ru.teamname.projectname.entity.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.thymeleaf.standard.expression.Each;
import ru.teamname.projectname.entity.packs.Category;
import ru.teamname.projectname.entity.packs.Pack;
import ru.teamname.projectname.entity.packs.Question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "createdDate"})
        }
)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private Integer timeForReading = 0; // in seconds

    @Column(nullable = false, updatable = false)
    private Integer timeForAnswering = 60; // in seconds

    @Column(nullable = false, updatable = false)
    private Integer maxRounds = 1;

    @Column(nullable = false, updatable = false)
    private Integer countOfCategories = 2;

    @Column(nullable = false)
    private Integer nowRound = 0;

    @Column(nullable = false)
    private List<Round> round = new ArrayList<>();

    @OneToMany(mappedBy = "gameId", fetch = FetchType.EAGER)
    private List<PlayerStatus> players = new ArrayList<>();

    @Column(nullable = false)
    @JsonIgnore
    private Integer chooserIndex = 0;

    @OneToOne
    @Column(nullable = false)
    private PlayerStatus chooser = new PlayerStatus();

    @ManyToMany
    @JsonIgnore
    private List<Pack> usingPacks = new ArrayList<>();






    @OneToMany(mappedBy = "gameId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<QuestionStatus> questions = new ArrayList<>();

    @Column(nullable = false)
    private Boolean online;

    @Column(updatable = false)
    @CreatedDate
    private Date createdDate;
}
