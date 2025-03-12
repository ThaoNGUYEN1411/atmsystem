package co.simplon.atmsystem.service;

import co.simplon.atmsystem.banking.Account;
import co.simplon.atmsystem.banking.Bank;
import co.simplon.atmsystem.output.Message;

public class RequestBalance {
    private Bank bank;
    private Message message;

    public Bank getBank() {
        return bank;
    }

    public Message getMessage() {
        return message;
    }
    //Cannot invoke "co.simplon.output.Message.returnMessage(String)" because "this.message" is null
    //need to init Bank and Message when init object
    public RequestBalance() {
        this.bank = new Bank();
        this.message = new Message();
    }

    public void requestBalance(String accountNumber) {
        if (bank.isBankAvailable()){
            bank.fileResponse();
            Account account = bank.findAccountByAccountNumber(accountNumber);
            if (account != null) {
                message.returnMessage("Your balance is :" + account.getBalance());
            } else {
                message.returnMessage("Account not found");
            }
        }else {
            message.returnMessage("Sorry, Bank is not available");
        }
    }
}
