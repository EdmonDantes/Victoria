package ru.teamname.projectname.entity.localization;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LocalizationString {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String locale = "en-US";

    @Column(nullable = false)
    private String string;
}
