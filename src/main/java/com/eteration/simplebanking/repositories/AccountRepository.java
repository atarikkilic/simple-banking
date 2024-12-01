package com.eteration.simplebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eteration.simplebanking.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}