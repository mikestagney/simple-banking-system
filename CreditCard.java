package banking;

import java.util.Random;

public class CreditCard {
    private String cardNumber;
    private String PIN;
    private int balance;

    CreditCard() {
        cardNumber = createCardNumber();
        PIN = createPIN();
        balance = 0;
    }

    private String createCardNumber() {
        StringBuilder accountBuilder = new StringBuilder("400000");
        int luhnAlgorithmSum = 8;  // sum of 4 0 0 0 0 0 with odd digits multiplied by 2

        Random random = new Random();
        for (int i = 1; i < 10; i++) {
            int digit = random.nextInt(10);
            accountBuilder.append(digit);
            if (i % 2 == 1) {
                digit *= 2;
            }
            if (digit > 9) {
                digit -=9;
            }
            luhnAlgorithmSum += digit;
        }
        int checksum = 0;
        while ((checksum + luhnAlgorithmSum) % 10 != 0) {
            checksum++;
        }
        accountBuilder.append(checksum);
        return accountBuilder.toString();
    }
    private String createPIN() {
        StringBuilder pinBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            pinBuilder.append(random.nextInt(10));
        }
        return pinBuilder.toString();
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getPIN() {
        return PIN;
    }
    public int getBalance() {
        return balance;
    }
}
