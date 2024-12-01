package com.eteration.simplebanking.model;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(Double amount) {
        super(amount);
    }

    @Override
    public void execute(Account account) throws InsufficientBalanceException {
        account.withdraw(this.getAmount());
    }
}