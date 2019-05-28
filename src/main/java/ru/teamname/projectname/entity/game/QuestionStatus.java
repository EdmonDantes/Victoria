package ru.teamname.projectname.entity.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.teamname.projectname.entity.packs.Question;

import javax.persistence.*;

@Entity
@Data
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"questionId", "gameId"})
        }
)
public class QuestionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "questionId", nullable = false)
    private Question questionId;

    /**
     * 0 - It can be choose in this round
     * 1 - It was choose in this round
     * 2 - It can`t use in this round, but can use in next rounds
     * 3 - It can`t use in this round and can`t use in next rounds
     */


    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    @JsonIgnore
    private Game gameId;
}
