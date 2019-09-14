package ru.teamname.projectname.entity.packs;

import lombok.Data;
import ru.teamname.projectname.entity.localization.LocalizationString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<LocalizationString> questionString = new HashSet<>();

    @OneToMany
    private Set<LocalizationString> answerString = new HashSet<>();

    @OneToMany
    private Set<Category> category = new HashSet<>();

    @Column(nullable = false)
    private Integer scope = 0;
}
