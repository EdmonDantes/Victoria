package ru.teamname.projectname.entity.packs;

import lombok.Data;
import ru.teamname.projectname.entity.localization.LocalizationString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Category {

    @Id
    private Integer id;

    @OneToMany
    private Set<LocalizationString> name;


}
