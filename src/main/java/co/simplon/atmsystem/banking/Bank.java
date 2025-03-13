package co.simplon.atmsystem.banking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Bank {
    private Set<Account> accounts;
    private boolean bankAvailable;
    private String filePath = "src/main/resources/accountsData.csv";

    public Bank() {
        this.accounts = loadFromFile(filePath);
        this.bankAvailable = true;
    }

    public boolean isBankAvailable() {
        return bankAvailable;
    }

    public void setBankAvailable(boolean bankAvailable) {
        this.bankAvailable = bankAvailable;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public Set<Account> fileResponse() {
        return loadFromFile(filePath);
    }

    public Account findAccountByAccountNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private Set<Account> loadFromFile(String filePath) {
        Set<Account> accounts = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String accountNumber = parts[0].trim();
                    Long balance = Long.parseLong(parts[1].trim());
                    accounts.add(new Account(accountNumber, balance));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error read file: " + e.getMessage());
        }
        return accounts;
    }

    public boolean withdraw(Account account, int amount){
        updateBalance(account, amount);
        return account.getBalance() > amount;
        //and another conditions decided by business
    }

    private void updateBalance(Account account, int amount){
        long newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("account,balance");
            bw.newLine();
            for (Account account : accounts) {
                bw.write(account.getAccountNumber() + "," + account.getBalance());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + accounts +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
