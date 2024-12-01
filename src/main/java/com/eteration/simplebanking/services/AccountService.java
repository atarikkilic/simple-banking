package com.eteration.simplebanking.services;

import org.springframework.stereotype.Service;
import com.eteration.simplebanking.model.Account;

@Service
public class AccountService {

    public Account findAccount(String accountNumber) {
        // Mock implementation
        return new Account("Kerem Karaca", accountNumber);
    }

    public void save(Account account) {
        // Mock implementation for saving account
    }
}