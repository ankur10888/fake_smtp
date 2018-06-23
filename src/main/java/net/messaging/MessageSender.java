package net.messaging;

import net.messaging.domain.Message;

import java.io.IOException;
import java.io.Writer;

public abstract class MessageSender {

    protected Writer networkWriter;
    protected Writer console;

    public MessageSender(Writer networkWriter, Writer console) {
        this.networkWriter = networkWriter;
        this.console = console;
    }

    public void sendMessage(Message message) {
        try {
            doSendMessage(message);
        } catch (Exception ex) {
            // TODO Handle exception
            ex.printStackTrace();
        }
    }

    protected abstract void doSendMessage(Message message) throws IOException;

}
