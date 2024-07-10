package com.amazon.ata.handlingexceptions;

import java.math.BigDecimal;

import com.amazon.ata.handlingexceptions.exceptions.InsufficientFundsException;

/**
 * This class represents a checking bank account, which includes methods that 
 * allow money to be deposited or withdrawn from the account.
 */
public class CheckingAccount implements BankAccount {
    
    /**
     * Identification number associated with the account
     */
    private String accountId;
    
    /**
     * Amount of money in the account.
     */
    private BigDecimal balance;
    
    /*
     * Validator helper class for validating input.
     */
    private Validator validator = new Validator();

    public CheckingAccount(String accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    /**
     * Deposit amount to account.
     * 
     * @param amount of money to deposit
     * @return value of account after the deposit
     */
    @Override
    public BigDecimal deposit(BigDecimal amount) {
        validator.validate(amount);
        balance = balance.add(amount);
        return balance;
    }

    /**
     * Withdraw amount from account.
     * 
     * @param amount of money to withdraw
     * @return value of account after the withdraw
     * @throws InsufficientFundsException if account does not have enough funds to withdraw amount
     */
    @Override
    public BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException {
        validator.validate(amount);
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            System.out.println("Withdrawal amount " + amount + " was sucessful. your new balance is " + balance);
            return balance;
        }else {
            throw new InsufficientFundsException("Insufficient funds. Withdrawal Failed.");
        }

    }

    @Override
    public BigDecimal getBalance() {
        return balance;    }
}
