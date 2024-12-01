package com.eteration.simplebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;

@RestController
@RequestMapping("/account/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{accountNumber}/credit")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber,
                                                    @RequestBody DepositTransaction deposit) {
        Account account = accountService.findAccount(accountNumber);
        try {
            System.out.println("Before post - approvalCode: " + deposit.getApprovalCode());
            account.post(deposit);
            System.out.println("After post - approvalCode: " + deposit.getApprovalCode());
            accountService.save(account);
            System.out.println("After save - approvalCode: " + deposit.getApprovalCode());
            return ResponseEntity.ok(new TransactionStatus("OK", deposit.getApprovalCode()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{accountNumber}/debit")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber,
                                                   @RequestBody WithdrawalTransaction withdrawal)
            throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.post(withdrawal);
        accountService.save(account);
        TransactionStatus status = new TransactionStatus("OK", withdrawal.getApprovalCode());
        return ResponseEntity.ok(status);
    }
}