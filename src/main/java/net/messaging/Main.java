package net.messaging;

import net.messaging.domain.Message;
import net.messaging.smtp.SmtpMessageSender;

import java.io.*;

public class Main {
    private static Writer network;
    private static Writer console;

    public static void setNetwork(Writer network) {
        Main.network = network;
    }

    public static void setConsole(Writer console) {
        Main.console = console;
    }

    public static void main(String... args) {
        Message message = new Message(args[0], args[1]);
        MessageSender messageSender = new SmtpMessageSender(network, console);

        messageSender.sendMessage(message);
    }
}