package ru.teamname.projectname.entites;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Player implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true, length = 80)
    private String username;

    @NotNull
    @Column(length = 60)
    private String password;

    @Column(unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Pack> buyingPacks;

    @NotNull
    @Column(updatable = false)
    @CreatedDate
    private Date registerTime;
}
