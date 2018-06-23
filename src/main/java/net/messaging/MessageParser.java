package net.messaging;

import net.messaging.domain.Message;
import net.messaging.domain.MessageType;

public class MessageParser {

    public Message parseMessage(String[] args) {
        if (args.length == 3) {
            assert args[0].equals("-im");
            return new Message(MessageType.IM, args[1], args[2]);
        }
        return new Message(args[0], args[1]);
    }
}
