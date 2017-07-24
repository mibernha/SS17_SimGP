package PrinterQueues;

import java.util.ArrayList;

public class FutureEventList {
    ArrayList<PrintJob> prioOnePlotQueue = new ArrayList<>();
    ArrayList<PrintJob> prioNullPlotQueue = new ArrayList<>();
    ArrayList<PrintJob> prioOnePrintQueue = new ArrayList<>();
    ArrayList<PrintJob> prioNullPrintQueue = new ArrayList<>();

    Plotter plotter = new Plotter();
    Printer printer = new Printer();

    public int currentSimTime = 0;
    public int currentStartTimePlot = 0;
    public int currentStartTimePrint = 0;
    public int currentEndTimePlot = 0;
    public int currentEndTimePrint = 0;
    public int currentProcessTimePlot = 0;
    public int currentProcessTimePrint = 0;
    public boolean currentPlotterActive = false;
    public boolean currentPrinterActive = false;


    public void addToPrioOnePlotQueue(PrintJob pj) {
        prioOnePlotQueue.add(pj);
    }

    public void addToPrioNullPlotQueue(PrintJob pj) {
        prioNullPlotQueue.add(pj);
    }

    public void removeFromPrioOnePlotQueue() {
        prioOnePlotQueue.remove(0);
    }

    public void removeFromPrioNullPlotQueue() {
        prioNullPlotQueue.remove(0);
    }

    public void addToPrioOnePrintQueue(PrintJob pj) {
        prioOnePrintQueue.add(pj);
    }

    public void addToPrioNullPrintQueue(PrintJob pj) {
        prioNullPrintQueue.add(pj);
    }

    public void removeFromPrioOnePrintQueue() {
        prioOnePrintQueue.remove(0);
    }

    public void removeFromPrioNullPrintQueue() {
        prioNullPrintQueue.remove(0);
    }


    public void printFELHeader() {
        //Print line for line the Future Event list - Formatting it here:
        //Sim-Time, Start-Time-Plot, Start-Time-Print, ProcesstimePlot, ProcesstimePrint, End-Time-Plot, End-Time-Print,, length-P0-Plot
        //lenght-P1-Plot, length-P0-Print, length-P1-Print, Status-Plot, Status-Print
        System.out.format("%15s%15s%15s%15s%15s%15s%15s%15s%15s%15s%15s%15s%15s%n", "Sim-Time", "StartPlot", "StartPrint", "ProcessPlot", "ProcessPrint", "EndPlot", "EndPrint"
                , "Prio0Plot", "Prio1Plot", "PlotActive", "Prio0Print", "Prio1Print", "PrintActive");
        System.out.format("%15d%15d%15d%15d%15d%15d%15d%15d%15d%15s%15d%15d%15s%n", currentSimTime, currentStartTimePlot, currentStartTimePrint,
                currentProcessTimePlot, currentProcessTimePrint, currentEndTimePlot, currentEndTimePrint, prioNullPlotQueue.size(), prioOnePlotQueue.size(),
                currentPlotterActive, prioNullPrintQueue.size(), prioOnePrintQueue.size(), currentPrinterActive);


    }
    @SuppressWarnings("Duplicates")
    public void anotherPrintFEL() {
        int avgProcessTimePlot = 0;
        int avgProcessTimePrint = 0;
        int numbOfPlots = prioNullPlotQueue.size() + prioOnePlotQueue.size();
        int numbOfPrints = prioNullPrintQueue.size() + prioOnePrintQueue.size();
        if(numbOfPlots < 1) {
            numbOfPlots = 1;
        }
        if(numbOfPrints < 1) {
            numbOfPrints = 1;
        }

        while(!prioOnePlotQueue.isEmpty() || !prioNullPlotQueue.isEmpty() || !prioOnePrintQueue.isEmpty() || !prioNullPrintQueue.isEmpty()) {
            PrintJob pj;
            if(!currentPlotterActive) {
                if(!prioOnePlotQueue.isEmpty()) {
                    pj = prioOnePlotQueue.get(0);
                    removeFromPrioOnePlotQueue();
                    currentStartTimePlot = currentSimTime;
                    currentProcessTimePlot = pj.getProcessTime();
                    avgProcessTimePlot += pj.getProcessTime();
                    currentEndTimePlot = currentSimTime + currentProcessTimePlot;
                    currentPlotterActive = true;
                } else if(!prioNullPlotQueue.isEmpty()) {
                    pj = prioNullPlotQueue.get(0);
                    removeFromPrioNullPlotQueue();
                    currentStartTimePlot = currentSimTime;
                    currentProcessTimePlot = pj.getProcessTime();
                    avgProcessTimePlot += pj.getProcessTime();
                    currentEndTimePlot = currentSimTime + currentProcessTimePlot;
                    currentPlotterActive = true;
                }
            }
            if(!currentPrinterActive) {
                if(!prioOnePrintQueue.isEmpty()) {
                    pj = prioOnePrintQueue.get(0);
                    removeFromPrioOnePrintQueue();
                    currentStartTimePrint = currentSimTime;
                    currentProcessTimePrint = pj.getProcessTime();
                    avgProcessTimePrint += pj.getProcessTime();
                    currentEndTimePrint = currentSimTime + currentProcessTimePrint;
                    currentPrinterActive = true;
                } else if(!prioNullPrintQueue.isEmpty()) {
                    pj = prioNullPrintQueue.get(0);
                    removeFromPrioNullPrintQueue();
                    currentStartTimePrint = currentSimTime;
                    currentProcessTimePrint = pj.getProcessTime();
                    avgProcessTimePrint += pj.getProcessTime();
                    currentEndTimePrint = currentSimTime + currentProcessTimePrint;
                    currentPrinterActive = true;
                }
            }

            System.out.format("%15d%15d%15d%15d%15d%15d%15d%15d%15d%15s%15d%15d%15s%n", currentSimTime, currentStartTimePlot, currentStartTimePrint,
                    currentProcessTimePlot, currentProcessTimePrint, currentEndTimePlot, currentEndTimePrint, prioNullPlotQueue.size(), prioOnePlotQueue.size(),
                    currentPlotterActive, prioNullPrintQueue.size(), prioOnePrintQueue.size(), currentPrinterActive);

            if(currentPrinterActive || currentPlotterActive) {
                if(currentEndTimePlot < currentEndTimePrint) {
                    currentSimTime = currentEndTimePlot;
                    currentPlotterActive = false;
                    currentEndTimePlot = 999999;
                    currentProcessTimePlot = 0;
                    currentStartTimePlot = 0;
                } else {
                    currentSimTime = currentEndTimePrint;
                    currentPrinterActive = false;
                    currentEndTimePrint = 999999;
                    currentProcessTimePrint = 0;
                    currentStartTimePrint = 0;
                }
            }
        }
        System.out.format("%15d%15d%15d%15d%15d%15d%15d%15d%15d%15s%15d%15d%15s%n", currentSimTime, currentStartTimePlot, currentStartTimePrint,
                currentProcessTimePlot, currentProcessTimePrint, currentEndTimePlot, currentEndTimePrint, prioNullPlotQueue.size(), prioOnePlotQueue.size(),
                currentPlotterActive, prioNullPrintQueue.size(), prioOnePrintQueue.size(), currentPrinterActive);

        System.out.println("Average Processtime Plotter: " + avgProcessTimePlot/numbOfPlots);
        System.out.println("Average Processtime Printer: " + avgProcessTimePrint/numbOfPrints);
    }
}
