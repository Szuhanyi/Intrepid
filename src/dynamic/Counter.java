package dynamic;

import services.Attributes;
import view.MainWindow;

/**
 * Increments the T variable by every Rate seconds
 *
 * @author Gyorgy
 */
public class Counter extends Thread {

    private boolean running;
    private boolean paused;
    private int rate;
    private MainWindow main;
    private int t;

    /**
     * rate = 4 by default
     */
    public Counter() {
        running = true;
        paused = false;
        rate = 1;
    }

    /**
     *  Sleep intervals in seconds
     * @param sleepTime
     */
    public Counter(MainWindow mw, int sleepTime) {
        this(mw);
        this.rate = sleepTime;
    }

    public Counter(MainWindow mainWindow) {
        this();
        main = mainWindow;
    }

    public void run() {
        while (running) {
            try {
                sleep(rate * 1000);
                if (!paused) {
                    t++;
                    main.setT(t);
                    Attributes.setT(t);
                    System.out.println(t);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void setT(int tt) {
        t = tt;
    }
    public void stopCounting() {
        running = false;
    }

    public void pauseCounting() {
        paused = true;
    }

    public void continueCounting() {
        paused = false;
    }
}
