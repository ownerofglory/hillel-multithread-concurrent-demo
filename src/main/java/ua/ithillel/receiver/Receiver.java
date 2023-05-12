package ua.ithillel.receiver;

public class Receiver implements Runnable {
    private MessageQueue messageQueue;
    private String name;

    public Receiver(MessageQueue messageQueue, String name) {
        this.messageQueue = messageQueue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (messageQueue) {
                    if (messageQueue.isEmpty()) {
                        messageQueue.wait();
                    }

                    Message message = messageQueue.getMessage();
                    System.out.println(name + " Processing message: "+ message);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Receiver interrupted");
            }
        }
    }
}
