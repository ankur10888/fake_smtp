package net.messaging;

import net.messaging.domain.Message;

import java.io.IOException;
import java.io.Writer;

public abstract class MessageSender {

    protected static final String LINE_BREAK = "\n";

    protected Writer networkWriter;
    private Writer console;
    private MessageValidator messageValidator;

    public MessageSender(Writer networkWriter, Writer console, MessageValidator messageValidator) {
        this.networkWriter = networkWriter;
        this.console = console;
        this.messageValidator = messageValidator;
    }

    public void sendMessage(Message message) {
        try {
            if (messageValidator.isValid(message)) {
                doSendMessage(message);
            } else {
                sendError(message);
            }
        } catch (Exception ex) {
            // TODO Handle exception
            ex.printStackTrace();
        }
    }

    private void sendError(Message message) throws IOException {
        console.write(String.format("Invalid email address: %s%s", message.getRecipient(), LINE_BREAK));
    }

    protected abstract void doSendMessage(Message message) throws IOException;

}
