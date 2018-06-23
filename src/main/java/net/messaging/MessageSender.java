package net.messaging;

import net.messaging.domain.Message;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

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
        List<String> errorMessages = messageValidator.validate(message);
        try {
            if (errorMessages.isEmpty()) {
                doSendMessage(message);
            } else {
                sendError(errorMessages);
            }
        } catch (Exception ex) {
            // TODO Handle exception
            ex.printStackTrace();
        }
    }

    private void writeToConsole(String error) {
        try {
            console.write(error);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void writeToNetwork(String message) {
        try {
            networkWriter.write(message);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void sendError(List<String> messages) throws IOException {
        messages.forEach(this::writeToConsole);
    }

    protected abstract void doSendMessage(Message message) throws IOException;

}
