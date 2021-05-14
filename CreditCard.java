package banking;

import java.util.Random;

public class CreditCard {
    private final String cardNumber;
    private final String PIN;
    private int balance;

    CreditCard() {
        cardNumber = createCardNumber();
        PIN = createPIN();
        balance = 0;
    }
    CreditCard(String number, String pin, int currentBalance) {
        cardNumber = number;
        PIN = pin;
        balance = currentBalance;
    }

    private String createCardNumber() {
        StringBuilder accountBuilder = new StringBuilder("400000");
        int luhnAlgorithmSum = 8;  // sum of 4 0 0 0 0 0 with odd digits multiplied by 2

        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            int digit = random.nextInt(10);
            accountBuilder.append(digit);
            if (i % 2 == 0) {
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
    public void addFunds(int income) {
        balance += income;
    }
    public void transferOutFunds(int transfer) {
        balance -= transfer;
    }
    public boolean checkLuhnAlgorithm(String number) {
        int checkNum = Character.getNumericValue(number.charAt(number.length() - 1));
        int luhnAlgorithmSum = 0;
        for (int i = 0; i < number.length() - 2; i++) {
            int digit = Character.getNumericValue(number.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
            }
            if (digit > 9) {
                digit -=9;
            }
            luhnAlgorithmSum += digit;
        }
        return (checkNum + luhnAlgorithmSum) % 10 == 0;
    }



}
