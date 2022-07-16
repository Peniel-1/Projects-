package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

    Scanner userInput = new Scanner(System.in);
    private final Menu menu;
    private double balance;
    private Log loggy;


    private TreeMap<String, Snack> snackList;


    //STOCKS ITEMS
    public void stockItems() {
        File stockList = new File("vendingmachine.csv");
        try (Scanner stock = new Scanner(stockList)) {
            while (stock.hasNextLine()) {
                String nextLine = stock.nextLine();
                if (nextLine.contains("Chip")) {
                    String[] chipArray = nextLine.split("\\|");
                    snackList.put(chipArray[0], new Chip(chipArray[0], chipArray[1], Double.parseDouble(chipArray[2])));
                }
                if (nextLine.contains("Candy")) {
                    String[] candyArray = nextLine.split("\\|");
                    snackList.put(candyArray[0], new Candy(candyArray[0], candyArray[1], Double.parseDouble(candyArray[2])));
                }
                if (nextLine.contains("Drink")) {
                    String[] drinkArray = nextLine.split("\\|");
                    snackList.put(drinkArray[0], new Drink(drinkArray[0], drinkArray[1], Double.parseDouble(drinkArray[2])));
                }
                if (nextLine.contains("Gum")) {
                    String[] GumArray = nextLine.split("\\|");
                    snackList.put(GumArray[0], new Gum(GumArray[0], GumArray[1], Double.parseDouble(GumArray[2])));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //DISPLAYS ITEMS
    public void DisplayItems() {
        for (Map.Entry<String, Snack> snac : snackList.entrySet()) {
            //String key = snac.getKey();
            Snack value = snac.getValue();
            if (value.getQuantity() > 0) {
                System.out.println(value.getSlot() + " | " + value.getName() + " | " + value.getPurchasePrice() + " | " + value.getQuantity() + " in stock");
            } else if (value.getQuantity() == 0) {
                System.out.println(value.getSlot() + " | " + value.getName() + " | " + value.getPurchasePrice() + " | " + "Sold Out");
            }
        }
    }


   /* public void rerunFirstMenu() {
        String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

        if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
            // display vending machine items
            DisplayItems();
            //continue;

        } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
            secondMenuSelection();
        } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
            System.out.println("Goodbye");
            System.exit(0);
        }
    }*/


    //SECOND MENU (NEEDS PURCHASE AND FINISH OPTIONS)
    public void secondMenuSelection() {
        System.out.println();
        System.out.println();
        System.out.println("Current Money Provided: $" + this.balance);
        System.out.println();
        System.out.println("(1) Feed Money \n(2) Select Product \n(3) Finish Transaction");
        System.out.print("Please select '1', '2', or '3' >>>> ");
        int menu2Choice = Integer.parseInt(userInput.nextLine());
        if (menu2Choice == 1) {
            insertMoney();
        } else if (menu2Choice == 2) {
            purchase();
        } else if (menu2Choice == 3) {
            cashOut();
        } else if (menu2Choice != 1 || menu2Choice != 2 || menu2Choice != 3) {
            System.out.println();
            System.out.println();
            System.out.println("**Invalid Choice**");
        }


    }
    //ADD MONEY MENU
    public void insertMoney() {
        System.out.println("");
        System.out.println("Please insert cash amount in whole dollars:");
        String insertedMoney = userInput.nextLine();
        if (insertedMoney.contains(".") || insertedMoney.matches("[a-zA-Z]+")) {
            System.out.println("Invalid format");
        } else {
            int insertedMoney2 = Integer.parseInt(insertedMoney);
            this.balance = balance + insertedMoney2;
            System.out.println();
            System.out.println("Current Balance: $" + this.balance);
            loggy.write("Inserted Money", insertedMoney, String.valueOf(this.balance));
        }
    }


    //MAKE A PURCHASE
    public void purchase() {
        DisplayItems();
        System.out.print("Enter the code of the item you want >>>> ");
        String code = userInput.nextLine();
        for (Map.Entry<String, Snack> snac : snackList.entrySet()) {
            String key = snac.getKey();
            Snack value = snac.getValue();
            if (code.equals(key)) {
                if (value.getPurchasePrice() < balance && value.quantity > 0) {
                    value.dispenseSnack();
                    this.balance = balance - value.getPurchasePrice();
                    System.out.println("Remaining balance: $" + this.balance);
                    System.out.println("****************************");
                    loggy.write(value.getName(), String.valueOf(value.getPurchasePrice()), String.valueOf(this.balance));
                   secondMenuSelection();
                } else if (value.getPurchasePrice() > balance) {
                    System.out.println("Insufficient Funds");
                   secondMenuSelection();
                } else if (value.quantity == 0) {
                    value.soldOut();
                   secondMenuSelection();
                }
            } /*else if (!code.equals(key)) {
                System.out.println("Invalid choice");
                 secondMenuSelection();
            } */
        }
    }


    public void cashOut() {
        Double balanceBefore = balance;
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;

        while (this.balance - 0.25 >= 0) {
            this.balance -= 0.25;
            quarterCount++;
        }
        while (this.balance - 0.10 >= 0) {
            this.balance -= 0.10;
            dimeCount++;
        }
        while (this.balance - 0.5 >= 0) {
            this.balance -= 0.5;
            nickelCount++;
        }
        System.out.println("");
        System.out.println("****************************");
        System.out.println("*clink-clink*");
        System.out.println("Quarters returned:  " + quarterCount);
        System.out.println("Dimes returned: " + dimeCount);
        System.out.println("Nickels returned:  " + nickelCount);
        System.out.println("****************************");
        System.out.println("Current Balance: $" + this.balance);
        loggy.write("Cash Given", String.valueOf(balanceBefore), String.valueOf(this.balance));

    }


    public void run() {
        stockItems();
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                DisplayItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                secondMenuSelection();
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.out.println("Goodbye");
                System.exit(0);
            }
        }
    }


    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
        this.snackList = new TreeMap<>();
        this.loggy = new Log();
    }


    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
