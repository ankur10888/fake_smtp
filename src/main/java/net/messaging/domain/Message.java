package net.messaging.domain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Message {
    private List<String> recipients = new LinkedList<>();
    private String message;

    public Message(String recipient, String message) {
        addRecipients(recipient);
        this.message = message;
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
}
