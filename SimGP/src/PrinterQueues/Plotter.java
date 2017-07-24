package PrinterQueues;

public class Plotter extends Thread {

    public void plot(PrintJob pj) {
        try {
            Thread.sleep(pj.getProcessTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
