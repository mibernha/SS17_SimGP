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
    public int currentEndTimePlot = 9999;
    public int currentEndTimePrint = 9999;
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

    //get next time from next event
    public void printFEL() {
        while(!prioOnePrintQueue.isEmpty() || !prioNullPrintQueue.isEmpty() || !prioNullPlotQueue.isEmpty() || !prioOnePlotQueue.isEmpty() || !printer.ready() || !plotter.ready()) {
            //first check if printer or plotter is ready, if yes look for matching print-jobs
            if (!currentPrinterActive) {
                if (!prioOnePrintQueue.isEmpty()) {
                    PrintJob pj = prioOnePrintQueue.get(0);
                    if(pj.getStartTimeOffset() <= currentEndTimePrint && pj.getStartTimeOffset() <= currentEndTimePlot) {
                        currentSimTime = pj.getStartTimeOffset();
                        currentStartTimePrint = currentSimTime;
                        currentProcessTimePrint = pj.getProcessTime();
                        currentEndTimePrint = currentSimTime + currentProcessTimePrint;
                        printer.print(pj);
                        currentPrinterActive = true;
                        removeFromPrioOnePrintQueue();
                    }
                } else if (!prioNullPrintQueue.isEmpty()) {
                    PrintJob pj = prioNullPrintQueue.get(0);
                    if(pj.getStartTimeOffset() <= currentEndTimePrint && pj.getStartTimeOffset() <= currentEndTimePlot) {
                        currentSimTime = pj.getStartTimeOffset();
                        currentStartTimePrint = currentSimTime;
                        currentProcessTimePrint = pj.getProcessTime();
                        currentEndTimePrint = currentSimTime + currentProcessTimePrint;
                        printer.print(pj);
                        currentPrinterActive = true;
                        removeFromPrioNullPrintQueue();
                    }
                }
            } else if (!currentPlotterActive) {
                if (!prioOnePlotQueue.isEmpty()) {
                    PrintJob pj = prioOnePlotQueue.get(0);
                    if(pj.getStartTimeOffset() <= currentEndTimePrint && pj.getStartTimeOffset() <= currentEndTimePlot) {
                        currentSimTime = pj.getStartTimeOffset();
                        currentStartTimePlot = currentSimTime;
                        currentProcessTimePlot = pj.getProcessTime();
                        currentEndTimePlot = currentSimTime + currentProcessTimePlot;
                        plotter.plot(pj);
                        currentPlotterActive = true;
                        removeFromPrioOnePlotQueue();
                    }
                } else if (!prioNullPlotQueue.isEmpty()) {
                    PrintJob pj = prioNullPlotQueue.get(0);
                    if(pj.getStartTimeOffset() <= currentEndTimePrint && pj.getStartTimeOffset() <= currentEndTimePlot) {
                        currentSimTime = pj.getStartTimeOffset();
                        currentStartTimePlot = currentSimTime;
                        currentProcessTimePlot = pj.getProcessTime();
                        currentEndTimePlot = currentSimTime + currentProcessTimePlot;
                        plotter.plot(pj);
                        currentPlotterActive = true;
                        removeFromPrioNullPlotQueue();
                    }
                }
            //none of the printer/Plotter is ready, so we take the next lowest time, an end of a plot/print
            //Options for lowest: StartTimes Plot/Print, EndTime Plot/Print
            } else {
               if(currentEndTimePlot < currentEndTimePrint) {
                   currentSimTime = currentEndTimePlot;
                   currentProcessTimePlot = 0;
                   currentStartTimePlot = 0;
                   currentPlotterActive = false;
                   System.out.println("Print < Plot");
               } else {
                   currentSimTime = currentEndTimePrint;
                   currentProcessTimePrint = 0;
                   currentStartTimePlot = 0;
                   currentPrinterActive = false;
                   System.out.println("Print > Plot");
               }
            }

            System.out.format("%15d%15d%15d%15d%15d%15d%15d%15d%15d%15s%15d%15d%15s%n", currentSimTime, currentStartTimePlot, currentStartTimePrint,
                    currentProcessTimePlot, currentProcessTimePrint, currentEndTimePlot, currentEndTimePrint, prioNullPlotQueue.size(), prioOnePlotQueue.size(),
                    currentPlotterActive, prioNullPrintQueue.size(), prioOnePrintQueue.size(), currentPrinterActive);
        }
    }
}
