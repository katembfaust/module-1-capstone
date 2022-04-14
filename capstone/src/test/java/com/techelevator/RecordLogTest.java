package com.techelevator;
import com.techelevator.RecordLog;
import com.techelevator.ChangeDispensed;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class RecordLogTest {

    private File log = new File("Log.txt");

    ChangeDispensed purchaseWorkFlow = new ChangeDispensed();

    Scanner scanner = new Scanner(log);

    RecordLog testLogger = new RecordLog();

    @Before
    public void setUp() {
        try {
            new PrintWriter(log).close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }


    }

    public RecordLogTest() throws FileNotFoundException {
    }


    @Test
    public void print_to_log_feed_money() throws IOException {
        purchaseWorkFlow.setCurrentMoney(10.00);
        double logMoneyFed = purchaseWorkFlow.getCurrentMoney() - 5.00;

        testLogger.printToLogFeedMoney(purchaseWorkFlow.getCurrentMoney(), logMoneyFed);


        String expected = scanner.nextLine();

        String destContent = Files.readString(log.toPath());

        assertEquals(expected.trim(), destContent.trim());

    }

    @Test
    public void print_to_log_item() throws IOException {
        purchaseWorkFlow.setCurrentMoney(10.00);
        double logMoneyFed = purchaseWorkFlow.getCurrentMoney() - 5.00;
        String location = "Magic Kingdom";
        String name = "chips";

        testLogger.printToLogItem(name, location, purchaseWorkFlow.getCurrentMoney(), logMoneyFed);


        String expected = scanner.nextLine();

        String destContent = Files.readString(log.toPath());

        assertEquals(expected.trim(), destContent.trim());
    }
    @Test
    public void print_to_log_give_change() throws IOException {
        purchaseWorkFlow.setCurrentMoney(10.00);
        double logMoneyFed = purchaseWorkFlow.getCurrentMoney() - 5.00;

        testLogger.printToLogChange(purchaseWorkFlow.getCurrentMoney(), logMoneyFed);


        String expected = scanner.nextLine();

        String destContent = Files.readString(log.toPath());

        assertEquals(expected.trim(), destContent.trim());

    }
}