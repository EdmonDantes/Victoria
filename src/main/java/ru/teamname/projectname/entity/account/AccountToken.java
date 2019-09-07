package ru.teamname.projectname.entity.account;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AccountToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true, length = 128)
    private String token;

    @ManyToOne
    private Account account;
}
