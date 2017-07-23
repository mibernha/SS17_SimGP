package PrinterQueues;

public class Plotter extends Thread {
    boolean isReady = true;

    public void plot(PrintJob pj) {
        isReady = false;
        try {
            Thread.sleep(pj.getProcessTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isReady = true;
    }

    public boolean ready() {
        return isReady;
    }
}
