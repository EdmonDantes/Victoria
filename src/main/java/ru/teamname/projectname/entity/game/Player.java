package ru.teamname.projectname.entity.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import ru.teamname.projectname.entity.packs.Pack;
import ru.teamname.projectname.entity.packs.Question;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "players")
public class Player implements Serializable  {

    public Player() {}

    /**
     * For JSON
     * @param id id in mysql
     */
    public Player(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true, length = 80)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false, length = 80)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "game_points", nullable = false)
    private Integer countOfGamePoints = 0;

    @Column(name = "count_games", nullable = false)
    private Integer countGames = 0;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Pack> buyingPacks = new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<Question> seeQuestion = new HashSet<>();

    @JsonIgnore
    @Column(name = "register_date_and_time", updatable = false)
    @CreatedDate
    private Date registerTime = new Date();
}
