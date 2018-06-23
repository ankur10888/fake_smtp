package net.messaging.domain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Message {

    // Default to Mail
    private MessageType messageType = MessageType.EMAIL;
    private List<String> recipients = new LinkedList<>();
    private String message;

    public Message(String recipient, String message) {
        addRecipients(recipient);
        this.message = message;
    }

    public Message(MessageType messageType, String recipient, String message) {
        addRecipients(recipient);
        this.message = message;
        this.messageType = messageType;
    }

    private void addRecipients(String recipientInput) {
        String[] recipientsInput = recipientInput.split(",");
        this.recipients.addAll(Arrays.asList(recipientsInput));
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
