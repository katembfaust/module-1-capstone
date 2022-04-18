package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.System.exit;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	private DecimalFormat formatter = new DecimalFormat("#0.00");

	public void run() {
//		creates object of FileSplitter to retrieve the list of items
		FileSplitter fileSplitter = new FileSplitter();
		fileSplitter.splitFile();
		List<Item> itemList = fileSplitter.getItemList();
		try {
			while (true) {
//				creates object for ChangeDispensed & Record logger to maintain and record purchase flow
				ChangeDispensed purchaseWorkFlow = new ChangeDispensed();
				RecordLog logger = new RecordLog();
				Scanner scanner = new Scanner(System.in);
				String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

				if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

					for (Item item : itemList) {
						System.out.println(item.getName() + " | " + item.getQuantity() + " in stock");
					}

				} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					while (!choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

						if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
							System.out.print("Enter dollar amount (ex. 1, 2, 5, 10) >>> ");

							String moneyInputStr = scanner.nextLine();
							//checks to see if the user has inserted enough money
							int moneyInput = 0;
							boolean loop = false;
							while (!loop) {
								try {
									moneyInput = Integer.parseInt(moneyInputStr);
									if (moneyInput > 0) {
										loop = true;
									} else {
										throw new Exception();
									}
								} catch (Exception e) {
									System.out.print("Please enter valid dollar amount >>> ");
									moneyInputStr = scanner.nextLine();
								}
							}
						//	shows the feed money on the console
							logger.printToLogFeedMoney(purchaseWorkFlow.getCurrentMoney(), moneyInput);
							purchaseWorkFlow.feedMoney(moneyInput);
							System.out.println(" ");
							System.out.println("Current Money Provided: " + formatter.format(purchaseWorkFlow.getCurrentMoney()));
						} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

							for (Item item : itemList) {
								System.out.println(item.getLocation() + " | "  + item.getName() + " | " + "$" + item.getPrice());
							}
							// get location input, ensure its validity
							System.out.println(" ");
							System.out.print("Enter location >>> ");
							String userLocationInput = scanner.nextLine();
							String itemSelected = "";
//							iterates over the items to find the correct location
							for (Item item : itemList) {
								if (userLocationInput.equalsIgnoreCase(item.getLocation())) {
									itemSelected += item.getName();
								}
							}
//							checks for invalid/null location
							if (itemSelected.equals("")) {
								System.out.println("Selected location not available.");
								break;
							}
							System.out.println(" ");
							int itemQuantity = 0;
							double itemPrice = 0;
//							iterates Items to find selected item
							for (Item item : itemList) {
								if (itemSelected.equals(item.getName())) {
									itemQuantity += item.getQuantity();
									itemPrice += item.getPrice();
								}
							}
							double currentMoney = purchaseWorkFlow.getCurrentMoney();
							if (itemQuantity > 0) {
								if (currentMoney >= itemPrice) {
									// begins dispense process by updating new money amount after purchase
									System.out.println(itemSelected + " dispensed. ");
									System.out.println("Cost: " + formatter.format(itemPrice));
									purchaseWorkFlow.purchase(itemPrice);
									System.out.println("Money Remaining: " + formatter.format(purchaseWorkFlow.getCurrentMoney()));
									String sound = "";
//									initiates the sound effect for the item
									for (Item item : itemList) {
										if (itemSelected.equals(item.getName())) {
											item.dispenseItem();
											sound += item.getSound();
										}
									}
//									Records purchase information to Record Log & also check below for insufficient funds or sold out
									logger.printToLogItem(itemSelected, userLocationInput, currentMoney, purchaseWorkFlow.getCurrentMoney());
									System.out.println(sound);
								} else {
									System.out.println("Insufficient funds.");
								}
							} else {
								System.out.println("Selected item is sold out.");
								break;
							}


						} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
//							begins the process of getting change
							double moneyToReturn = purchaseWorkFlow.getCurrentMoney();
							int numberOfCents = (int) (moneyToReturn * 100);
							int quarterQty = purchaseWorkFlow.quartersToReturn(numberOfCents);
							int dimeQty = purchaseWorkFlow.dimesToReturn();
							int nickelQty = purchaseWorkFlow.nickelsToReturn();
							numberOfCents = purchaseWorkFlow.getNumberOfCents();
//							adds the change amount to the RecordLogger
							logger.printToLogChange(moneyToReturn, numberOfCents);
							moneyToReturn = (double) numberOfCents / 100;
							purchaseWorkFlow.setCurrentMoney(moneyToReturn);
							System.out.println("Change Returned");
							System.out.println("Quarters Returned: " + quarterQty);
							System.out.println("Dimes Returned: " + dimeQty);
							System.out.println("Nickels Returned: " + nickelQty);


						}
					}

				} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
					exit(0);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
		}
	}


	public static void main(String[] args) {


		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

	}

}