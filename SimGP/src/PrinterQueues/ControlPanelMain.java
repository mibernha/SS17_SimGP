package PrinterQueues;

import java.util.Random;

public class ControlPanelMain {

    public static FutureEventList fel;

    public static void main(String[] args) {
        //Create an empty FutureEventList
        fel = new FutureEventList();

        generateRandomEvents(1000);

        System.out.println("Future Event List: ");
        fel.printFELHeader();
        fel.anotherPrintFEL();
    }

    public static void generateRandomEvents(int numberOfRandomPrintJobs) {
        //Create Random-Generator with uniform distribution
        Random rnd = new Random();
        //Generate random PrintJobs that will be added to the Priority-Queues, stop aber x(20) Jobs
        for(int i = 0; i < numberOfRandomPrintJobs; i++) {
            //Generate random number of pages (between 1 and 50), random type of print (1 in 20 is a plot or A5, 18 in 20 are A4), Priority (1 in 10 is a lecturer)
            int numbOfPages = rnd.nextInt(45)+6;
            int chosePrintType = rnd.nextInt(20);
            int chosePriority = rnd.nextInt(10);
            //set values of those random numbers
            PrintJob newPJ;
            int priority = 0;

            if(chosePriority < 9) {
                priority = 0;
            } else {
                priority = 1;
            }

            if(chosePrintType == 0) {
                newPJ = new PrintJob(JobType.PLOT, (numbOfPages + 30), priority);
            } else if (chosePrintType == 1) {
                newPJ = new PrintJob(JobType.A5_PRINT, numbOfPages, priority);
            } else {
                newPJ = new PrintJob(JobType.A4_PRINT, numbOfPages, priority);
            }

            //add the PrintJob to it's priorityQueue
            if(priority == 0) {
                if(chosePrintType == 0) {
                    fel.addToPrioNullPlotQueue(newPJ);
                } else {
                    fel.addToPrioNullPrintQueue(newPJ);
                }
            } else {
                if(chosePrintType == 0) {
                    fel.addToPrioOnePlotQueue(newPJ);
                } else {
                    fel.addToPrioOnePrintQueue(newPJ);
                }
            }

        }
    }
}
