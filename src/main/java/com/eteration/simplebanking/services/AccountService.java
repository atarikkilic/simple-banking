package com.eteration.simplebanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findAccount(String accountNumber) {
        return accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}