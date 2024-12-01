package com.eteration.simplebanking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Account {
    @Id
    private String accountNumber;

    private String owner;
    private Double balance;
    private Date createDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        this.createDate = new Date();
    }

    protected Account() {} // For JPA

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(Double amount) {
        this.balance += amount;
    }

    public void withdraw(Double amount) throws InsufficientBalanceException {
        if (this.balance < amount) {
            throw new InsufficientBalanceException();
        }
        this.balance -= amount;
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.execute(this);
        transactions.add(transaction);
    }
}