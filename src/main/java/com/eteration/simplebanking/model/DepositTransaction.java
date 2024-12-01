package com.eteration.simplebanking.model;

public class DepositTransaction extends Transaction {

    public DepositTransaction(Double amount) {
        super(amount);
    }

    @Override
    public void execute(Account account) {
        account.deposit(this.getAmount());
    }
}