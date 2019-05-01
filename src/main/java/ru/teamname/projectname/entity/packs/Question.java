package ru.teamname.projectname.entity.packs;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"question", "price"})}
)
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String question;

    @NotNull
    private Integer price;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Answer.class)
    @JoinTable
    private List<Answer> answers;

    public Question(){}
}
