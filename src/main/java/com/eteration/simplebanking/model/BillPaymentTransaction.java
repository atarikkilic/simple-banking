package com.eteration.simplebanking.model;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("BillPaymentTransaction")
public class BillPaymentTransaction extends Transaction {

    private String payee;

    public BillPaymentTransaction(String payee, Double amount) {
        super(amount);
        this.payee = payee;
    }

    protected BillPaymentTransaction() {
        super();
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    @Override
    public void execute(Account account) throws InsufficientBalanceException {
        account.withdraw(this.getAmount());
    }

    @Override
    public String toString() {
        return "Bill Payment to " + payee + " with amount " + getAmount();
    }
}