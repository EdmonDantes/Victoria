package ru.teamname.projectname.entity.localization;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LocalizationPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Currency currency;

    private Integer price;
}
