package com.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bank.Entity.Account;

public interface AccountRepository extends JpaRepository<Account ,Long> {

}
