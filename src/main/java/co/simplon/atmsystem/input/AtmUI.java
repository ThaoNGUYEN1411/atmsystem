package co.simplon.atmsystem.input;

import co.simplon.atmsystem.banking.Account;
import co.simplon.atmsystem.service.RequestBalance;
import co.simplon.atmsystem.service.Withdraw;

import java.util.Scanner;

public class AtmUI {
    private final Scanner scanner;
    private final RequestBalance requestBalance;
    private final Withdraw withdraw;
    private int availableCash;

    public AtmUI() {
        this.scanner = new Scanner(System.in);
        this.requestBalance = new RequestBalance();
        this.withdraw = new Withdraw();
        this.availableCash = 5000;
    }

    public void start() {
        System.out.println("You have already inserted your card");

        //client insert card with infos for validate code pin
        Card card = new Card("123","456", new Account(null, null), false);
        //example account number pour identified card
        String accountNumber = "1234";

        //verify card already unlocked
        if (!card.isUnlocked()) {
            unlock(card);
        } else {
            authenticate(card);
        }

        displayMenu();
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                withdraw(accountNumber);
                break;
            case "2":
                requestBalance(accountNumber);
                break;
            case "3":
                System.out.println("Goodbye!");
                scanner.close();
                return;
            default:
                System.out.println("Invalid option, try again!");
        }
    }

    private void unlock(Card card) {
        int t = 0;
        while (t < 3) {
            System.out.print("Enter code pin: ");

            String enteredPin = scanner.nextLine();
            if (card.checkPin(enteredPin)) {
                card.setUnlocked(true);
                System.out.println("Code pin valid with success!");
                break;
            } else {
                System.out.println("try again, you have 3 times to try");
            }
            t = t + 1;
        }
    }

    private void displayMenu() {
        System.out.println("\n1. Withdraw");
        System.out.println("2. Request balance");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    private void authenticate(Card card) {
        int t = 0;

        while (t < 3) {
            System.out.print("Enter code pin: ");

            String enteredPin = scanner.nextLine();
            if (card.checkPin(enteredPin)) {
                System.out.println("Authentication successful!");
                break;
            } else {
                System.out.println("try again, you have 3 times to try");
            }
            t = t + 1;
        }
    }

    private void withdraw(String accountNumber) {
        System.out.print("enter amount: ");
        int amount = scanner.nextInt();
        if (amount % 10 != 0 || amount > 500) {
            System.out.println("Invalid amount! Please enter a multiple of 10 and less 500 euros");
        } else if (amount > availableCash) {
            System.out.println("Sorry, not enough cash in the ATM.");
        } else {
            withdraw.withdraw(accountNumber, amount);
        }
    }

    private void requestBalance(String accountNumber) {
        requestBalance.requestBalance(accountNumber);
    }

}
