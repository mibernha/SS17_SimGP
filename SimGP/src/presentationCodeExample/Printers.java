package presentationCodeExample;
public class Printers extends Thread {
	PrintJob printJob;
	
	public Printers(PrintJob printJob) {
		this.printJob = printJob;
	}
	
	public void run() {
		if(printJob.client.equals("Lecturer")) {
			System.out.println("Printing for Lecturer - Expected duration: " + printJob.duration + "s");
		} else {
			System.out.println("Printing for Student - Expected duration: " + printJob.duration + "s");
		}
		try {
			Thread.sleep(printJob.duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
