package org.kursovoi.client.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageJobScheduler {

    private final MessageSender messageService;

    @Autowired
    public MessageJobScheduler(MessageSender messageService) {
        this.messageService = messageService;
    }

    @Scheduled(fixedDelay = 1000L)
    public String sendMessageJob(CommandType command, String request) {
        return messageService.sendMessage(command, request);
    }

}
