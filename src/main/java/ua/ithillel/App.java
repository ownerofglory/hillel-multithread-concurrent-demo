package ua.ithillel;

import ua.ithillel.counter.Counter;
import ua.ithillel.receiver.Message;
import ua.ithillel.receiver.MessageQueue;
import ua.ithillel.receiver.Receiver;
import ua.ithillel.receiver.Sender;
import ua.ithillel.thread.MyRunnable;
import ua.ithillel.thread.MyThread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        MessageQueue messageQueue = new MessageQueue();

        Sender sender = new Sender(messageQueue);
        Receiver receiver = new Receiver(messageQueue, "First");
        Receiver receiver1 = new Receiver(messageQueue, "Last");
        Thread thread = new Thread(receiver);
        thread.start();

        new Thread(receiver1).start();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            Message message = new Message(data);

            sender.send(message);
        }

//        Counter counter = new Counter();
//
//        Thread thread1 = new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("First: " + counter.incrementAndGet());
//            }
//        });
//
//        Thread thread2 = new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("Second: " + counter.incrementAndGet());
//            }
//        });
//
//        thread1.start();
//        thread2.start();


//        try {
//            StringBuffer first = new StringBuffer();
//            StringBuffer second = new StringBuffer();
//
//            Thread fisrtThread = new Thread() {
//                @Override
//                public void run() {
//                    try (BufferedReader br
//                                 = new BufferedReader(new FileReader("file1.txt"))) {
//
//                        br.lines().forEach(line -> {
//                            first.append(line);
//                            first.append("\n");
//                        });
//
//                        System.out.println("First");
//                        System.out.println(first);
//
//                    } catch (FileNotFoundException e) {
//                        System.out.println("File not found " + e.getMessage());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            };
//
//            Runnable r = () -> {
//                try (BufferedReader br
//                             = new BufferedReader(new FileReader("file2.txt"))) {
//
//                    br.lines().forEach(line -> {
//                        second.append(line);
//                        second.append("\n");
//                    });
//
//                    System.out.println("Second");
//                    System.out.println(second);
//
//                } catch (FileNotFoundException e) {
//                    System.out.println("File not found " + e.getMessage());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            };
//            Thread secondThread = new Thread(r);
//
//            Thread daemonThread = new Thread(() -> {
//                for (int i = 0; i < 1000; i++) {
//                    try {
//                        if (Thread.currentThread().isInterrupted()) {
//                            break;
//                        }
////                        Thread.sleep(1);
//                        System.out.printf("Daemon thread %d%n", i);
//                    } finally {
//
//                    }
//                }
//            });
//
//            daemonThread.setDaemon(true);
//
//            fisrtThread.setPriority(Thread.MAX_PRIORITY);
//            secondThread.setPriority(Thread.MIN_PRIORITY);
//
//            daemonThread.start();
//            fisrtThread.start();
//            secondThread.start();
//
//            daemonThread.interrupt();
//
//            fisrtThread.interrupt();


//            fisrtThread.join();
//            secondThread.join();

//            System.out.println("Text");
//            System.out.println(first.append(second));
//        } finally {
//
//        }
//        catch (InterruptedException e) {
//            System.out.println("main thread interrupted");
//        }

//        MyThread myThread = new MyThread();
//        myThread.start();
//
//        MyRunnable myRunnable = new MyRunnable();
//        Thread myRunnbleThread = new Thread(myRunnable);
//
//        myRunnbleThread.start();

        System.out.println("Main exited");

//        Thread thread = Thread.currentThread();
//        System.out.println(thread.getName());

    }

}
