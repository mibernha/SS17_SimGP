package PrinterQueues;

public class Printer extends Thread {


    public void print(PrintJob pj) {
        try {
            Thread.sleep(pj.getProcessTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
