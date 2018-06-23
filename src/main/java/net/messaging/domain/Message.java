package net.messaging.domain;

public class Message {
    private String recipient;
    private String message;

    public Message(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }
}
