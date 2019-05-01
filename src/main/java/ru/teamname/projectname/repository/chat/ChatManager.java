package ru.teamname.projectname.repository.chat;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamname.projectname.entity.chat.Chat;
import ru.teamname.projectname.entity.chat.ChatMessage;

@Service
@Data
public class ChatManager {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRepository chatRepository;

    public Chat createChat(){
        Chat chat = new Chat();
        return chatRepository.save(chat);
    }

    public Chat addMessageToChat(Chat chat, ChatMessage message) {
        if (chat != null && chat.getId() != null && message != null && message.getMessage() != null && message.getMessage().length() > 0 && message.getPlayerId() != null && message.getPlayerId().getId() != null) {
            message = chatMessageRepository.save(message);
            chatRepository.addMessageToChat(chat.getId(), message.getId());
            chat.getMessages().add(message);
        }
        return chat;
    }
}
