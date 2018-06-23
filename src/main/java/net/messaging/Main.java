package net.messaging;

import net.messaging.domain.Message;

import java.io.Writer;

public class Main {
    private static Writer network;
    private static Writer console;

    private static MessageValidator messageValidator = new MessageValidator();
    private static MessageParser messageParser = new MessageParser();

    public static void setNetwork(Writer network) {
        Main.network = network;
    }

    public static void setConsole(Writer console) {
        Main.console = console;
    }

    public static void main(String... args) {
        Message message = messageParser.parseMessage(args);

        MessageSender messageSender = MessageSender
                .getSenderFor(message.getMessageType(), network, console, messageValidator);

        messageSender.sendMessage(message);
    }
}