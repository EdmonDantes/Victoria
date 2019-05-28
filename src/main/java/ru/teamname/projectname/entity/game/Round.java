package ru.teamname.projectname.entity.game;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private List<String> chosenCategories = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<QuestionStatus> questions = new ArrayList<>();



}
