package org.kursovoi.client.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MessageSender {

    @Autowired
    private TcpClientGateway tcpClientGateway;

    public String sendMessage(CommandType command, String request) {
        byte[] responseBytes = tcpClientGateway.send((command.name() + ", " + request).getBytes(StandardCharsets.UTF_8));
        return new String(responseBytes, StandardCharsets.UTF_8);
    }
}
