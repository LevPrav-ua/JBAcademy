package banking;

import java.util.Random;

public class CreditCard {
    private String cardNumber;
    private int PIN;
    private int balance = 0;

    CreditCard(String cardNumber, int PIN) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
    }

    int getPIN() {
        return PIN;
    }
    String getCardNumber() {
        return cardNumber;
    }
    public double getBalance() {
        return balance;
    }


    public CreditCard() {
        cardNumber = generateNumber();
        PIN = generatePIN();
    }

    private int generatePIN() {
        Random random = new Random();
        return random.nextInt(9_000) + 1_000;
    }

    private String generateNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("400000");
        sb.append(String.valueOf((long) random.nextInt(1_000_000_000) + 4_000_000_000L));
        while (!isValidLuhn(sb.toString())) {
            sb.delete(0, 17);
            sb.append("400000");
            sb.append(String.valueOf((long) random.nextInt(1_000_000_000) + 4_000_000_000L));
        }
        return sb.toString();
    }

    private boolean isValidLuhn(String value) {
        int sum = Character.getNumericValue(value.charAt(value.length() - 1));
        int parity = value.length() % 2;
        for (int i = value.length() - 2; i >= 0; i--) {
            int summand = Character.getNumericValue(value.charAt(i));
            if (i % 2 == parity) {
                int product = summand * 2;
                summand = (product > 9) ? (product - 9) : product;
            }
            sum += summand;
        }
        return (sum % 10) == 0;
    }

    public boolean check(String cardNumber) {
        if (!isValidLuhn(cardNumber)) {
            return false;
        }
        return true;
    }

    public void changeBalance(int inc) {
        balance += inc;
    }
}
