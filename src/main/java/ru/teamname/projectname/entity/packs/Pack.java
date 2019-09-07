package ru.teamname.projectname.entity.packs;

import lombok.Data;
import ru.teamname.projectname.entity.localization.LocalizationString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<LocalizationString> name;

    private Integer price;

    private Integer minAge;

}
