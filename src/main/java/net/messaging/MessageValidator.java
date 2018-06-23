package net.messaging;

import net.messaging.domain.Message;

import java.util.LinkedList;
import java.util.List;

public class MessageValidator {


    public List<String> validate(Message message) {
        List<String> errorMessages = new LinkedList<>();

        if (message.getRecipient() == null) {
            errorMessages.add(String.format("Invalid email address: %s\n", message.getRecipient()));
        } else if (!message.getRecipient().contains("@")) {
            errorMessages.add(String.format("Invalid email address: %s\n", message.getRecipient()));
        }

        if (message.getMessage() == null || message.getMessage().trim().isEmpty()) {
            errorMessages.add("Cannot send an email with no body.\n");
        }

        return errorMessages;
    }
}
