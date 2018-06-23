package net.messaging.im;

import net.messaging.MessageSender;
import net.messaging.MessageValidator;
import net.messaging.domain.Message;

import java.io.Writer;

public class InstantMessageSender extends MessageSender {

    public InstantMessageSender(Writer networkWriter, Writer console, MessageValidator messageValidator) {
        super(networkWriter, console, messageValidator);
    }

    @Override
    protected void doSendMessage(Message message) {
        initializeChatConnection();
        deliverMessage(message);
        disconnect();
    }

    private void disconnect() {
        writeToNetwork("disconnect\n");
    }

    private void deliverMessage(Message message) {
        message.getRecipients()
                .forEach(recipient ->
                        writeToNetwork(String.format("<%s>(%s)\n", recipient, message.getMessage())));

    }

    private void initializeChatConnection() {
        writeToNetwork("connect chat\n");
    }
}
