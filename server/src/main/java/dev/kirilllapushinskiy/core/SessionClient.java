package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.communication.Message;
import dev.kirilllapushinskiy.communication.Serializator;
import dev.kirilllapushinskiy.communication.Session;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class SessionClient implements Session {

    private final SocketAddress address;

    private Message message;

    public SessionClient(SocketAddress address) {
        this.address = address;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public Message getMessage() {
        return message;
    }

    String currentCommand = null;

    int state = 0;

    String response = null;

    public void sendResponse(DatagramChannel channel) throws IOException {

        if (address == null) throw new IllegalStateException("Address is null!");
        if (message == null) throw new IllegalStateException("Message is null!");

        ByteBuffer buffer = Serializator.serialize(message);
        channel.send(buffer, address);

    }

    public SocketAddress getAddress() {
        return address;
    }

    public void addHistory(String text) {
        if (history.size() == 11) {
            history.removeFirst();
        }
        history.add(text);
    }

    @Override
    public String getCurrentCommand() {
        return currentCommand;
    }

    @Override
    public void upState() {
        state += 1;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setCurrentCommand(String commandName) {
        currentCommand = commandName;
    }

    @Override
    public void setState(int i) {
        state = i;
    }

    @Override
    public String getResponse() {
        return response;
    }

}
