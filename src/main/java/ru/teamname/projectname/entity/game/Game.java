package ru.teamname.projectname.entity.game;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import ru.teamname.projectname.entity.packs.Category;
import ru.teamname.projectname.entity.packs.Question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(nullable = false, updatable = false)
    private String name;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Integer timeForReading;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Integer timeForAnswering;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Integer maxRounds;

    @NotNull
    @OneToMany
    private Set<Player> players;

    @NotNull
    @Column(nullable = false)
    private Player chooser;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;

    @NotNull
    @ManyToMany
    private List<Question> questions;

    @NotNull
    @Column(updatable = false)
    @CreatedDate
    private Date createdDate;

    @NotNull
    @Column(nullable = false)
    private Boolean online;
}
