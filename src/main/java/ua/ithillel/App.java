package ua.ithillel;

import ua.ithillel.counter.Counter;
import ua.ithillel.counter.LockCounter;
import ua.ithillel.counter.ReadWriteLockCounter;
import ua.ithillel.counter.SemaphoreCounter;
import ua.ithillel.receiver.Message;
import ua.ithillel.receiver.MessageQueue;
import ua.ithillel.receiver.Receiver;
import ua.ithillel.receiver.Sender;
import ua.ithillel.server.MyMultiThreadServer;
import ua.ithillel.server.MyWeatherServer;
import ua.ithillel.thread.MyRunnable;
import ua.ithillel.thread.MyThread;
import ua.ithillel.weather.client.OpenWeatherClient;
import ua.ithillel.weather.client.WeatherClient;
import ua.ithillel.weather.model.WeatherInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class App {
    private volatile static int number;
    private volatile static boolean ready;

    public static void main(String[] args) {
        WeatherClient weatherClient = new OpenWeatherClient(HttpClient.newHttpClient());


        try (MyWeatherServer server = new MyWeatherServer(8080, weatherClient)) {

            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // FIXME: uncomment to use ExecutorService with weatherClient
//        WeatherClient weatherClient = new OpenWeatherClient(HttpClient.newHttpClient());
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String city = scanner.nextLine();
//
//            Runnable weatherTask = () -> {
//                try {
//                    Thread.sleep(1000);
//                    WeatherInfo weatherByCity = weatherClient.getWeatherByCity(city);
//                    System.out.printf("Weather in [%s]: %s%n", city, weatherByCity);
//                } catch (InterruptedException e) {
//                    System.out.println("Task interrupted");
//                }
//            };
//
//            executorService.submit(weatherTask);
//        }
//
//        try {
//            List<Future<Integer>> futures
//                    =  executorService.invokeAll(List.of(() -> 1, () -> 1));
//
//
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        executorService.shutdown();


        // FIXME: uncomment to use ExecutorService with random number task
//        try {
//            ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//            Callable<Integer> randTask = () -> {
//                Thread.sleep(10 * 1000);
//                return (int) (Math.random() * 1000);
//            };
//
//            Future<Integer> randFuture = executorService.submit(randTask);
//            Future<Integer> randFuture2 = executorService.submit(randTask);
//
//            Integer integer = randFuture.get();
//            Integer integer2 = randFuture.get();
//            System.out.println(integer);
//            System.out.println(integer2);
//        } catch (ExecutionException ex) {
//            throw new RuntimeException(ex);
//        } catch (InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }


        // FIXME: uncomment to use Callable with FutureTask
//        try {
//
//            Callable<Integer> randTask = () -> {
//                Thread.sleep(10 * 1000);
//                return (int) (Math.random() * 1000);
//            };
//
//            FutureTask<Integer> randFutureTask = new FutureTask<>(randTask);
//
//            new Thread(randFutureTask).start();
//
//            Integer randInt = 0;
//            while (true) {
//                try {
//                    randInt = randFutureTask.get(1, TimeUnit.SECONDS);
//                    break;
//                } catch (TimeoutException e) {
//                    System.out.println("Task is not dome yet, try another time...");
//                }
//            }
//
//            System.out.println(randInt);

        // FIXME: uncomment to use Callable example with "gluing" 2 files

//            Callable<String> c1 = () -> {
//                try (BufferedReader br
//                             = new BufferedReader(new FileReader("file1.txt"))) {
//
//                    String fileContent = br.lines()
//                            .reduce((acc, val) -> acc + val + "\n")
//                            .orElseGet(() -> "");
//
//
//                    return fileContent;
//                } catch (IOException e) {
//                    System.out.println("Exception when reading from file");
//                }
//
//                return "";
//            };
//
//            Callable<String> c2 = () -> {
//                try (BufferedReader br
//                             = new BufferedReader(new FileReader("file2.txt"))) {
//
//                    String fileContent = br.lines()
//                            .reduce((acc, val) -> acc + val + "\n")
//                            .orElseGet(() -> "");
//
//
//                    return fileContent;
//                } catch (IOException e) {
//                    System.out.println("Exception when reading from file");
//                }
//
//                return "";
//            };
//
//
//            FutureTask<String> task1 = new FutureTask<>(c1);
//            FutureTask<String> task2 = new FutureTask<>(c2);
//
//            new Thread(task1).start();
//            new Thread(task2).start();
//
//            String s1 = task1.get();
//            String s2 = task2.get();
//
//            System.out.println(s1 + s2);

//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        // FIXME: uncomment to use server with multiple thread
//        try (MyMultiThreadServer server = new MyMultiThreadServer(8080)) {
//            server.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        // FIXME: uncomment to use Semaphore example

//        SemaphoreCounter counter = new SemaphoreCounter();
//        counter.incrementAndGet();
//
//        Thread t1 = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println("t1 " + counter.getCount());
//            }
//        });
//        Thread t2 = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println("t2 " + counter.getCount());
//            }
//        });
//        Thread t3 = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println("t3 " + counter.getCount());
//            }
//        });
//
//        t1.start();
//        t2.start();
//        t3.start();

        // FIXME: uncomment to use CountDownLatch
//        try {
//
//            CountDownLatch countDownLatch = new CountDownLatch(3);
//
//            Thread t1 = new Thread(() -> {
//                for (int i = 0; i < 1000; i++) {
//                    System.out.println("t1 " + i);
//                }
//
//                countDownLatch.countDown();
//            });
//
//            Thread t2 = new Thread(() -> {
//                for (int i = 0; i < 1000; i++) {
//                    System.out.println("t2 " + i);
//                }
//
//                countDownLatch.countDown();
//            });
//
//            Thread t3 = new Thread(() -> {
//                for (int i = 0; i < 1000; i++) {
//                    System.out.println("t3 " + i);
//                }
//
//                countDownLatch.countDown();
//            });
//
//            t1.start();
//            t2.start();
//            t3.start();
//
//            System.out.println("Executing main thread");
//
//            countDownLatch.await();
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//
//        }

        // FIXME: uncomment to use ReadWriteLock example
//        ReadWriteLockCounter counter = new ReadWriteLockCounter();
//
//        Thread t1 = new Thread(() -> {
//            for (int i = 0; i < 100_000; i++) {
//                System.out.println("t1 " + counter.incrementAndGet());
//            }
//        });
//        Thread t2 = new Thread(() -> {
//            for (int i = 0; i < 100_000; i++) {
//                System.out.println("t2 " + counter.getCount());
//            }
//        });
//        Thread t3 = new Thread(() -> {
//            for (int i = 0; i < 100_000; i++) {
//                System.out.println("t3 " + counter.getCount());
//            }
//        });



//        t1.start();
//        t2.start();
//        t3.start();

        // FIXME: uncomment to use volatile example
//        new Thread(() -> {
//            while (!ready) {
//                Thread.yield();
//            }
//
//            System.out.println(number);
//        }).start();
//
//        number = 20;
//        ready = true;


        // FIXME: uncomment to use message processing in a parallel thread
//        MessageQueue messageQueue = new MessageQueue();
//
//        Sender sender = new Sender(messageQueue);
//        Receiver receiver = new Receiver(messageQueue, "First");
//        Receiver receiver1 = new Receiver(messageQueue, "Last");
//        Thread thread = new Thread(receiver);
//        thread.start();
//
//        new Thread(receiver1).start();
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNext()) {
//            String data = scanner.nextLine();
//            Message message = new Message(data);
//
//            sender.send(message);
//        }

        // FIXME: uncomment to use synchronizing example
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


        // FIXME: uncomment to use Thread example
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

        // FIXME: uncomment to use basic Thread example
//        MyThread myThread = new MyThread();
//        myThread.start();
//
//        MyRunnable myRunnable = new MyRunnable();
//        Thread myRunnbleThread = new Thread(myRunnable);
//
//        myRunnbleThread.start();

        System.out.println("Main exited");

        // FIXME: uncomment to access current thread's object
//        Thread thread = Thread.currentThread();
//        System.out.println(thread.getName());

    }

}
