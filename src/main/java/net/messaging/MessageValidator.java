package net.messaging;

import net.messaging.domain.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageValidator {


    public List<String> validate(Message message) {
        List<String> errorMessages = new LinkedList<>();

        List<String> invalidRecipients = message.getRecipients()
                .stream()
                .filter(recipient -> !recipient.contains("@"))
                .collect(Collectors.toList());

        if(!invalidRecipients.isEmpty()) {

            if(invalidRecipients.size() == 1) {
                errorMessages.add(String.format("Invalid email address: %s\n", invalidRecipients.get(0)));
            } else {
                errorMessages.add("Invalid email addresses: " + String.join(" ", invalidRecipients) + "\n");
            }
        }

        if (message.getMessage() == null || message.getMessage().trim().isEmpty()) {
            errorMessages.add("Cannot send an email with no body.\n");
        }

        return errorMessages;
    }
}
