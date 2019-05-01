package ru.teamname.projectname.entity.chat;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Chat {

    public Chat(){}
    public Chat(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    private List<ChatMessage> messages = new ArrayList<>();

}
