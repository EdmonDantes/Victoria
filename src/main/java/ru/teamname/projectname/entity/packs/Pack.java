package ru.teamname.projectname.entity.packs;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    List<Category> categories;

}
