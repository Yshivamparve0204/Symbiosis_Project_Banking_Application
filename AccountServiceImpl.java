package com.Bank.Service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Bank.Dto.AccountDto;
import com.Bank.Entity.Account;
import com.Bank.Mapper.AccountMapper;
import com.Bank.Repository.AccountRepository;
import com.Bank.Service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository  accountRepository;
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}



	@Override
	public AccountDto createAccount(AccountDto AccountDto) {
		Account account = AccountMapper.mapToAccount(AccountDto);
		Account savedAccount=accountRepository.save(account);
	
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("account not found"));
		
		return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("account not found"));
		
	   double total= account.getBalance() + amount;
	   account.setBalance(total);
	   Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto withdrawl(long id, double amount) {
	
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("account not found"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Amount");
		}
		
	   double total= account.getBalance() - amount;
	   account.setBalance(total);
	   Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	 public List<AccountDto> getAllAccounts(){
		  List<Account> accounts = accountRepository.findAll();
		  return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account))
		  .collect(Collectors.toList());
		
	}



	@Override
	public void deleteAccountById(Long id) {
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("account not found"));
		
		accountRepository.deleteById(id);
	}
}
	
	


