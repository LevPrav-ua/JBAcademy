package banking;

import java.util.Scanner;

public class BankAccount {
    private CreditCard card;

    BankAccount() {
        card = new CreditCard();
        System.out.println("Your card has been created");
        showInfo();
    }

    BankAccount(String cardNumber, int PIN, int balance) {
        card = new CreditCard(cardNumber, PIN);
        card.changeBalance(balance);
    }

    private void showInfo() {
        System.out.println("Your card number:\n" +
                card.getCardNumber() + "\n" +
                "Your card PIN:\n" +
                card.getPIN());
    }


    public boolean login(Scanner scanner, BankDB db) {
        while(true){
            System.out.println("1. Balance\n" +
                    "2. Add income\n" +
                    "3. Do transfer\n" +
                    "4. Close account\n" +
                    "5. Log out\n" +
                    "0. Exit");
            int ans = scanner.nextInt();

            switch (ans) {
                case 1:
                    System.out.println("Balance:" + (int) card.getBalance());
                    break;
                case 2:
                    addIncome(scanner, db);
                    break;
                case 3:
                    doTransfer(scanner, db);
                    break;
                case 4:
                    deleteAccount(db);
                    return true;
                case 5:
                    System.out.println("You have successfully logged out!");
                    return true;
                case 0:
                    return false;
                default:
                    break;
            }
        }
    }

    private void deleteAccount(BankDB db) {
        db.deleteAccount(card.getCardNumber());
    }

    private void doTransfer(Scanner scanner, BankDB db) {
        System.out.println("Enter card number:");
        String cardNumber = scanner.next();
        if (card.check(cardNumber)) {
            if (cardNumber.equals(card.getCardNumber())) {
                System.out.println("You can't transfer money to the same account!");
            } else {
                if (db.accountExits(cardNumber)) {
                    System.out.println("Enter how much money you want to transfer:");
                    int money = scanner.nextInt();
                    if (money <= card.getBalance()) {
                        db.updateAccountBalance(cardNumber, money);
                        card.changeBalance(-1 * money);
                        db.updateAccountBalance(card.getCardNumber(), -1 * money);
                    } else {
                        System.out.println("Not enough money!");
                    }
                } else {
                    System.out.println("Such a card does not exist.");
                }
            }
        } else {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
        }
    }

    private void addIncome(Scanner scanner, BankDB db) {
        System.out.println("Enter income:");
        int inc = Math.max(scanner.nextInt(), 0);
        card.changeBalance(inc);
        db.updateAccountBalance(card.getCardNumber(), inc);
        System.out.println("Income was added!");
    }

    public CreditCard getCard() {
        return card;
    }
}
