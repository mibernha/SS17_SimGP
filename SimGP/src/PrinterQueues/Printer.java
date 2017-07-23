package PrinterQueues;

public class Printer extends Thread {

    boolean isReady = true;

    public void print(PrintJob pj) {
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
