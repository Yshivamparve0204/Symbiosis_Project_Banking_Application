package com.Bank.Service;

import java.util.List;

import com.Bank.Dto.AccountDto;


public interface AccountService {
	
	AccountDto createAccount(AccountDto AccountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id,double amount);
	
	AccountDto withdrawl(long id,double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccountById(Long id);
	

}
