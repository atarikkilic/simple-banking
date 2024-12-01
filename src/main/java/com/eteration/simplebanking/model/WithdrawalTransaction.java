package com.eteration.simplebanking.model;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("WithdrawalTransaction")
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(Double amount) {
        super(amount);
    }

    protected WithdrawalTransaction() {
        super();
    }

    @Override
    public void execute(Account account) throws InsufficientBalanceException {
        account.withdraw(this.getAmount());
    }
}