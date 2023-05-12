package ua.ithillel.receiver;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<Message> queue = new LinkedList<>();

    public void addMessage(Message message) {
        queue.add(message);
    }

    public Message getMessage() {
        return queue.remove();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
