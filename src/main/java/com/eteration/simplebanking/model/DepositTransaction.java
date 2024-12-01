package com.eteration.simplebanking.model;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction(Double amount) {
        super(amount);
        System.out.println("DepositTransaction created with approvalCode: " + getApprovalCode());
    }

    protected DepositTransaction() {
        super();
    }

    @Override
    public void setAmount(Double amount) {
        super.setAmount(amount);
    }

    @Override
    public void execute(Account account) {
        System.out.println("Executing deposit with approvalCode: " + getApprovalCode());
        account.deposit(this.getAmount());
    }
}