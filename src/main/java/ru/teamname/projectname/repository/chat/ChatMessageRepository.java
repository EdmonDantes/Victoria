package ru.teamname.projectname.repository.chat;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.chat.ChatMessage;

@Repository
@Transactional
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer> {

    @Query("select message from ChatMessage message where message.playerId = :playerId")
    Iterable<ChatMessage> getAllByPlayerId(@Param("playerId") Integer playerId);

//    @Query("update ChatMessage message set message.chatId = :chatId where message.id = :id")
//    @Modifying
//    void setChatId(@Param("id") Integer id, @Param("chatId") Integer chatId);

}
