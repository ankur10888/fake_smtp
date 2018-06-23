package net.messaging;

import net.messaging.domain.Message;

public class MessageValidator {

    public boolean isValid(Message message) {
        boolean valid = true;

        if (message.getRecipient() == null) {
            valid = false;
        } else if (!message.getRecipient().contains("@")) {
            valid = false;
        }

        return valid;
    }
}
