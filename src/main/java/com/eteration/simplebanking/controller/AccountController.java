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
            account.post(deposit);
            accountService.save(account);
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

    @PostMapping("/{accountNumber}/bill-payment")
    public ResponseEntity<TransactionStatus> billPayment(
            @PathVariable String accountNumber,
            @RequestBody BillPaymentTransaction billPayment)
            throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.post(billPayment);
        accountService.save(account);
        return ResponseEntity.ok(new TransactionStatus("OK", billPayment.getApprovalCode()));
    }
}