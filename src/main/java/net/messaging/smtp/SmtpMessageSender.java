package net.messaging.smtp;

import net.messaging.MessageSender;
import net.messaging.MessageValidator;
import net.messaging.domain.Message;

import java.io.IOException;
import java.io.Writer;

public class SmtpMessageSender extends MessageSender {

    public SmtpMessageSender(Writer networkWriter, Writer console, MessageValidator messageValidator) {
        super(networkWriter, console, messageValidator);
    }

    @Override
    public void doSendMessage(Message message) throws IOException {
        initializeSmtpConnection();
        deliverMessage(message);
        disconnect();
    }

    private void disconnect() throws IOException {
        networkWriter.write("disconnect\n");
    }

    private void deliverMessage(Message message) throws IOException {
        networkWriter.write(String.format("To: %s\n", message.getRecipient()));
        networkWriter.write(LINE_BREAK);
        //Add line break to message
        networkWriter.write(message.getMessage() + LINE_BREAK);
        networkWriter.write(LINE_BREAK);
    }

    private void initializeSmtpConnection() throws IOException {
        networkWriter.write("connect smtp\n");
    }
}
