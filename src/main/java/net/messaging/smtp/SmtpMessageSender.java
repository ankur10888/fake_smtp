package net.messaging.smtp;

import net.messaging.MessageSender;
import net.messaging.MessageValidator;
import net.messaging.domain.Message;

import java.io.Writer;

public class SmtpMessageSender extends MessageSender {

    public SmtpMessageSender(Writer networkWriter, Writer console, MessageValidator messageValidator) {
        super(networkWriter, console, messageValidator);
    }

    @Override
    public void doSendMessage(Message message) {
        initializeSmtpConnection();
        deliverMessage(message);
        disconnect();
    }

    private void disconnect() {
        writeToNetwork("disconnect\n");
    }

    private void deliverMessage(Message message) {
        message.getRecipients()
                .forEach(recipient ->
                        writeToNetwork(String.format("To: %s\n", recipient)));

        writeToNetwork(LINE_BREAK);
        //Add line break to message
        writeToNetwork(message.getMessage() + LINE_BREAK);
        writeToNetwork(LINE_BREAK);
    }

    private void initializeSmtpConnection() {
        writeToNetwork("connect smtp\n");
    }
}
