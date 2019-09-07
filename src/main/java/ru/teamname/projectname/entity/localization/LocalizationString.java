package ru.teamname.projectname.entity.localization;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class LocalizationString {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String locale;

    private String string;
}
