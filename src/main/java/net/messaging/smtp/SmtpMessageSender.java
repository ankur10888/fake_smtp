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
        writeToNetwork("disconnect\n");
    }

    private void deliverMessage(Message message) throws IOException {
        message.getRecipients()
                .forEach(recipient ->
                        writeToNetwork(String.format("To: %s\n", recipient)));

        writeToNetwork(LINE_BREAK);
        //Add line break to message
        writeToNetwork(message.getMessage() + LINE_BREAK);
        writeToNetwork(LINE_BREAK);
    }

    private void initializeSmtpConnection() throws IOException {
        writeToNetwork("connect smtp\n");
    }
}
