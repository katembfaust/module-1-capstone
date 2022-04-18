package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RecordLog {

    private File log = new File("Log.txt");
    //  Java class that formats doubles into desired decimal amount for dollars and cents
    private DecimalFormat formatter = new DecimalFormat("#0.00");


    PrintWriter logPrint = new PrintWriter(new FileOutputStream(log, true));

    public RecordLog() throws FileNotFoundException {
    }

    //  Java class that pulls and formats local date & time
    public String dateTime() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return date + " " + time;

    }

    public void printToLogFeedMoney(double currentMoney, double logMoneyfed) {
        String lineToPrint = dateTime() + " FEED MONEY: " + "$" + formatter.format(currentMoney) + " $" + formatter.format(logMoneyfed);
        logPrint.println(lineToPrint);
        logPrint.flush();



    }

    public void printToLogItem(String name, String location, double moneyBefore, double moneyAfter) {
        String lineToPrint = dateTime() + " " + name + " " + location + " $" + formatter.format(moneyBefore) + " $" +  formatter.format(moneyAfter);
        logPrint.println(lineToPrint);
        logPrint.flush();

    }

    public void printToLogChange(double initialMoney, double remainingMoney){
        String lineToPrint = dateTime() + " GIVE CHANGE: " + "$" + formatter.format(initialMoney) + " $" + formatter.format(remainingMoney);
        logPrint.println(lineToPrint);
        logPrint.flush();

    }
    public void closeLogPrint(){
        logPrint.close();
    }

}