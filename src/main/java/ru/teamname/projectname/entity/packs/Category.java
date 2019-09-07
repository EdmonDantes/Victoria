package ru.teamname.projectname.entity.packs;

import lombok.Data;
import ru.teamname.projectname.entity.localization.LocalizationString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Category {

    @Id
    public Integer id;

    @OneToOne
    public LocalizationString name;


}
