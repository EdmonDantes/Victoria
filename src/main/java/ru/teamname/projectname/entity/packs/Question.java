package ru.teamname.projectname.entity.packs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private Integer price;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Answer.class)
    @JsonIgnore
    private List<Answer> answers = new ArrayList<>();

    public Question(){}
}
