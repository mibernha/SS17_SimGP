package presentationCodeExample;
import java.util.LinkedList;
import java.util.Random;

public class ControlPanel {

	public static void main(String[] args) {
		int NUMBEROFEVENTS = 100;
		int probabilityStudent = 0;
		int probabilityPrintJob = 0;
		int duration = 0;
		int counter = 0;
				
		LinkedList<PrintJob> lecturerQueue = new LinkedList<>();
		LinkedList<PrintJob> studentQueue = new LinkedList<>();
		
		Plotters plotter1;
		Printers printer1;
		
		Random rnd = new Random();
		
		for(int i = 0; i < NUMBEROFEVENTS; i++) {
			//Random-Numbers [0;4], 0-3 are Students, 4 are Lecturers
			probabilityStudent = rnd.nextInt(5);
			//Random-Numbers [0;19], 0-17 are A4-PrintJobs, 18/19 are PlotJobs
			probabilityPrintJob = rnd.nextInt(20);
			if(probabilityStudent < 4) {
				//One in 5 PrintJobs is from a Lecturer, so < 4 are all Students
				if(probabilityPrintJob < 18) {
					//Random-Numbers [1;600], duration of printJob in seconds
					duration = rnd.nextInt(300)+1;
					lecturerQueue.add(new PrintJob(duration, "A4", "Lecturer"));
				} else {
					//Random-Numbers [200;600], duration of plotJob in seconds
					duration = rnd.nextInt(400)+201;
					lecturerQueue.add(new PrintJob(duration, "Plot", "Lecturer"));
				}
			} else {
				//One in 5 PrintJobs is from a Lecturer, so these are Lecturers
				if(probabilityPrintJob == 9) {
					//Random-Numbers [1;600], duration of printJob in seconds
					duration = rnd.nextInt(300)+1;
					studentQueue.add(new PrintJob(duration, "A4", "Student"));
				} else {
					//Random-Numbers [200;600], duration of plotJob in seconds
					duration = rnd.nextInt(400)+201;
					studentQueue.add(new PrintJob(duration, "Plot", "Student"));
				}
			}
		}
		System.out.println("STARTING ALL JOBS!");
		
		while(!lecturerQueue.isEmpty()) {
			//Do alle PrintJobs from the lecturerQueue
			if(lecturerQueue.get(counter).type.equals("A4")) {
				printer1 = new Printers(lecturerQueue.get(counter));
				printer1.start();
			} else {
				plotter1 = new Plotters(lecturerQueue.get(counter));
				plotter1.start();
			}
			lecturerQueue.remove(counter);
		}
		while(!studentQueue.isEmpty()) {
			//Do alle PrintJobs from the lecturerQueue
			if(studentQueue.get(counter).type.equals("A4")) {
				printer1 = new Printers(studentQueue.get(counter));
				printer1.start();
			} else {
				plotter1 = new Plotters(studentQueue.get(counter));
				plotter1.start();
			}
			studentQueue.remove(counter);
		}
		
		System.out.println("ALL JOBS DONE!");
	}
}
