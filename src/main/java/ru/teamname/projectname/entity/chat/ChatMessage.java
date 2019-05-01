package ru.teamname.projectname.entity.chat;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import ru.teamname.projectname.entity.game.Player;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "playerId", nullable = false, updatable = false)
    private Player playerId = new Player();

    @Column(nullable = false, updatable = false)
    private String message = "";

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate = new Date();
}
