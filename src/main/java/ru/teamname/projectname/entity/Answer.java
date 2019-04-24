package ru.teamname.projectname.entity;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(unique = true)
    private String answer;

    public Answer(){}

    public Answer(String string) {
        this.answer = string;
    }
    public Answer(Integer number) {
        this.answer = number.toString();
    }
    public Answer(Double number) {
        this.answer = number.toString();
    }
    public Answer(Number number) {
        this.answer = number.toString();
    }
}
