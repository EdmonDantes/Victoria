package ru.teamname.projectname.repository.chat;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.teamname.projectname.entity.chat.Chat;

@Repository
@Transactional
public interface ChatRepository extends CrudRepository<Chat, Integer> {

    @Query(nativeQuery = true, value = "INSERT INTO chat_messages(chat_id, messages_id) VALUES(:chatId, :messageId) ON DUPLICATE KEY UPDATE chat_id = :chatId, messages_id = :messageId")
    @Modifying
    void addMessageToChat(@Param("chatId") Integer chatId, @Param("messageId") Integer chatMessageId);
}
