package co.simplon.atmsystem.service;

import co.simplon.atmsystem.banking.Account;
import co.simplon.atmsystem.banking.Bank;
import co.simplon.atmsystem.output.Message;

public class Withdraw {
    private Bank bank;
    private Message message;

    public Withdraw() {
        this.bank = new Bank();
        this.message = new Message();
    }

    public void withdraw(String accountNumber, int amount) {
        if (bank.isBankAvailable()){
            bank.fileResponse();
            Account account = bank.findAccountByAccountNumber(accountNumber);
            if (bank.withdraw(account, amount)){
                message.returnMessage("Withdraw successfully! Give your money! thank you!");
            }else {
                message.returnMessage("Error withdraw, please check or try again!");
            }
        }else {
            message.returnMessage("Sorry, Bank is not available");
        }
    }
}
