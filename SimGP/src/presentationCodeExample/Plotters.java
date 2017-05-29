package presentationCodeExample;
public class Plotters extends Thread {
	PrintJob printJob;
	
	public Plotters(PrintJob printJob) {
		this.printJob = printJob;
	}
	
	public void run() {
		if(printJob.client.equals("Lecturer")) {
			System.out.println("Plotting for Lecturer - Expected duration: " + printJob.duration + "s");
		} else {
			System.out.println("Plotting for Student - Expected duration: " + printJob.duration + "s");
		}
		try {
			Thread.sleep(printJob.duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
