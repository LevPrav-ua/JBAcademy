package banking;

import java.util.Scanner;

public class BankingSystem {
    private Scanner scanner;
    private final BankDB db;

    BankingSystem(String dataBaseName) {
        db = new BankDB();
        db.setFileName(dataBaseName);
        db.createTable();
    }

    public void start() {
        scanner = new Scanner(System.in);
        boolean work = true;
        while(work){
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            int ans;
            ans = scanner.nextInt();

            switch (ans) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    work = logInAccount();
                    break;
                case 0:
                    work = false;
                    System.out.println("Bye!");
                    break;
                default:
                    break;
            }
        }
    }

    private boolean logInAccount() {
        System.out.println("Enter your card number:");
        String cardNumber = scanner.next();
        System.out.println("Enter your PIN:");
        int PIN = scanner.nextInt();
        BankAccount account = db.getAccountOrNull(cardNumber, PIN);

        if (account != null) {
            System.out.println("You have successfully logged in!");
            return account.login(scanner, db);
        } else {
            System.out.println("Wrong card number or PIN!");
        }
        return true;
    }

    private void createAccount() {
        db.addAccount( new BankAccount());
    }
}
