package com.amazon.ata.handlingexceptions;

import java.math.BigDecimal;

import com.amazon.ata.handlingexceptions.exceptions.InsufficientFundsException;
import com.amazon.ata.handlingexceptions.exceptions.InvalidInputException;
import com.amazon.ata.handlingexceptions.exceptions.TransactionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents a bank, which includes the functionality to transfer from
 * one BankAccount to another.
 */
public class Bank {
    private Logger log = LogManager.getLogger(Bank.class);
    private Validator validator = new Validator();

    /**
     * Transfer money from one account to another. 
     * 
     * @param fromAccount BankAccount to withdraw amount from
     * @param toAccount BankAccount to deposit amount into
     * @param amount of money to transfer.
     * @return true if transfer was successful, false if transfer fails due to insufficient funds
     */
    public boolean transfer(BankAccount fromAccount, BankAccount toAccount, BigDecimal amount) throws InvalidInputException {
        validator.validate(amount);

        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            return true;
        } catch (InsufficientFundsException e) {
            log.error("Transfer failed due to insufficient funds" + e.getMessage());
            return false;
        } catch (TransactionException e) {
            throw new RuntimeException(e);
        }
    }
}
