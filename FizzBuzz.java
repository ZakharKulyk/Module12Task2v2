import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FizzBuzz {
    private static Object monitor = new Object();

    int n;
    public int currentValue;
    BlockingQueue<String> queue;
    boolean canGo = true;


    Thread A = new Thread(() -> {
        System.out.println("Thread A started working");
        fizz();
        System.out.println("Thread A ended working");
    });
    Thread B = new Thread(() -> {
        System.out.println("Thread B started working");
        buzz();
        System.out.println("Thread  B ended working");
    });
    Thread C = new Thread(() -> {
        System.out.println("Thread C started working");
        fizzBuzz();
        System.out.println("Thread C ended working");
    });
    Thread D = new Thread(() -> {
        System.out.println("Thread D started working");
        number();
        System.out.println("Thread D ended working");
    });

    Thread E = new Thread(() -> {
        System.out.println("Thread E started");
        defaultNumber();
        System.out.println("Thread E ended");
    });


    public FizzBuzz(int n) {
        this.n = n;
        queue = new ArrayBlockingQueue(n);
        C.start();
        E.start();
        A.start();
        B.start();
        D.start();


        for (int i = 1; i <= n; i++) {
            synchronized (monitor) {
                try {
                    currentValue = i;
                    canGo = true;
                    monitor.wait();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void fizz() {
        while (true) {
            while (canGo) {
                if (currentValue == 0) {
                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                    continue;
                }
                if (currentValue % 3 == 0) {
                    try {
                        queue.put("fizz");
                        canGo = false;
                        synchronized (monitor) {
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

        }


    }

    public void buzz() {

        while (true) {
            while (canGo) {
                if (currentValue == 0) {
                    synchronized (monitor) {
                        monitor.notifyAll();
                        continue;
                    }
                }
                if (currentValue % 5 == 0) {
                    try {
                        queue.put("buzz");
                        canGo = false;
                        synchronized (monitor) {
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }


    }

    public void fizzBuzz() {

        while (true) {
            while (canGo) {
                if (currentValue == 0) {
                    synchronized (monitor) {
                        monitor.notifyAll();
                        continue;
                    }

                }
                if (currentValue % 5==0&& currentValue%3==0) {
                    try {
                        queue.put("fizzBuzz");
                        canGo = false;
                        synchronized (monitor) {
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

        }

    }

    public void defaultNumber() {
        while (true) {

            while (canGo) {
                if (currentValue == 0) {
                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                    continue;
                }
                if (currentValue % 3 != 0 && currentValue % 5 != 0) {
                    try {
                        queue.put(Integer.toString(currentValue));
                        canGo = false;
                        synchronized (monitor) {
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }


    }

    public void number() {

        while (true) {
            if (!(queue.isEmpty())) {
                try {
                    System.out.println(queue.take());

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
