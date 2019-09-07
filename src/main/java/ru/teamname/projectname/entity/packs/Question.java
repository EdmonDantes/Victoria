package ru.teamname.projectname.entity.packs;

import lombok.Data;
import ru.teamname.projectname.entity.localization.LocalizationString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<LocalizationString> questionString;

    @OneToMany
    private Set<LocalizationString> answerString;

    @OneToMany
    private Set<Category> category;

    private Integer price;
}
