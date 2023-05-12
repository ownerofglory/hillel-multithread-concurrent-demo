package ua.ithillel.receiver;

public class Sender {
    private MessageQueue messageQueue;

    public Sender(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    public void send(Message message) {
        synchronized (messageQueue) {
            messageQueue.addMessage(message);
            messageQueue.notify();
        }
    }
}
