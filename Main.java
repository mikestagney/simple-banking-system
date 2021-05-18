package banking;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static String userChoice;
    static CreditCard activeCard;
    static Database database;


    public static void main(String[] args) {

        String fileName = getDatabaseFilename(args);
        database = new Database(fileName);

        while (true) {
            mainMenu();
            userChoice = getInputFromUser();
            System.out.println();
            switch (userChoice.charAt(0)) {
                case ('1'):
                    CreditCard card = createCard();
                    database.addCardToDB(card);
                    break;
                case('2'):
                    loginAccount();
                    break;
                case ('0'):
                    exitApp();
                    break;
            }
        }
    }
    public static String getDatabaseFilename(String[] commandLineArgs) {
        String dbFilename = "default.db";
        if (commandLineArgs.length > 1) {
            for (int i = 0; i < commandLineArgs.length; i++) {
                if (commandLineArgs[i].equals("-fileName")) {
                    dbFilename = commandLineArgs[i + 1];
                    break;
                }
            }
        }
        return dbFilename;
    }


    public static void loginAccount() {
        boolean isCorrectAccount = false;
        System.out.println("Enter your card number:");
        String cardNum = getInputFromUser();
        System.out.println("Enter your PIN:");
        String pin = getInputFromUser();
        System.out.println();
        cardNum = cardNum.trim();
        pin = pin.trim();

        activeCard = database.findCard(cardNum, pin);
        if(activeCard != null) {
            System.out.println("You have successfully logged in!");
            System.out.println();
            isCorrectAccount = true;
            manageCard();
        }
        if (!isCorrectAccount) {
            System.out.println("Wrong card number or PIN!");
            System.out.println();
        }
    }
    public static void manageCard() {
        boolean keepManaging = true;
        while (keepManaging) {
            loggedInMenu();
            String choice = getInputFromUser();
            System.out.println();
            switch (choice.charAt(0)) {
                case('0'):
                    exitApp();
                    break;
                case('1'):
                    System.out.println("Balance: " + activeCard.getBalance());
                    System.out.println();
                    break;
                case('2'):
                    addIncome();
                    break;
                case('3'):
                    doTransfer();
                    break;
                case('4'):
                    deleteCard();
                    keepManaging = false;
                    break;
                case('5'):
                    keepManaging = false;
                    System.out.println("You have successfully logged out!");
                    System.out.println();
                    break;
            }
        }
    }
    public static CreditCard createCard() {
        CreditCard newCard = new CreditCard();
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(newCard.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.println(newCard.getPIN());
        System.out.println();
        return newCard;
    }
    public static void mainMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }
    public static void loggedInMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
    }
    public static void addIncome() {
        System.out.println("Enter income");
        int income = Integer.parseInt(getInputFromUser());
        activeCard.addFunds(income);
        database.addIncome(activeCard);
        System.out.println("Income was added!");
        System.out.println();
    }
    public static void doTransfer() {



    }
    public static void deleteCard() {
        database.deleteCard(activeCard);
        activeCard = null;
        System.out.println("The account has been closed!");
        System.out.println();
    }
    public static String getInputFromUser() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public static void exitApp() {
        System.out.println("Bye!");
        System.exit(0);
    }
}