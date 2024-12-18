package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date date;
    private Double amount;
    private String approvalCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public Transaction(Double amount) {
        this.date = new Date();
        this.amount = amount;
        this.approvalCode = UUID.randomUUID().toString();
    }

    protected Transaction() {
        this.date = new Date();
    }

    protected void setAmount(Double amount) {
        this.amount = amount;
        if (this.approvalCode == null) {
            this.approvalCode = UUID.randomUUID().toString();
            if (this.date == null) {
                this.date = new Date();
            }
        }
    }

    public Date getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
    public abstract void execute(Account account) throws InsufficientBalanceException;
}