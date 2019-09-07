package ru.teamname.projectname.entity.localization;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<LocalizationString> name;
}
