package co.simplon.atmsystem.input;

import co.simplon.atmsystem.banking.Account;

public class Card {
    private final String cardNumber;
    private String codePin;
    private Account account;
    private boolean unlocked;

    //can make default value unlocked is false
    public Card(String cardNumber, String codePin, Account account, boolean unlocked) {
        this.cardNumber = cardNumber;
        this.codePin = codePin;
        this.account = account;
        this.unlocked = unlocked;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCodePin() {
        return codePin;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setCodePin(String codePin) {
        this.codePin = codePin;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public boolean checkPin(String pin) {
        return pin.equals(codePin);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", codePin='" + codePin + '\'' +
                ", unlocked=" + unlocked +
                '}';
    }
}
