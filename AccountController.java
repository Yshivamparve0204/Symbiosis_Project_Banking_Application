package com.Bank.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bank.Dto.AccountDto;
import com.Bank.Service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	// add rest API for create account
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
	return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	
	}
	
    // get Account Rest API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
		AccountDto accountDto= accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
			@RequestBody Map<String ,Double> request){
	
	double amount = request.get("amount");
	AccountDto accountDto=accountService.deposit(id, amount);
	return ResponseEntity.ok(accountDto);
	
	}
	@PutMapping("/{id}/withdrawl")
	public ResponseEntity<AccountDto> withdrawl(@PathVariable Long id,
			@RequestBody Map<String ,Double> request){
	
	double amount = request.get("amount");
	AccountDto accountDto=accountService.withdrawl(id, amount);
	return ResponseEntity.ok(accountDto);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccount(){
		 List<AccountDto>accounts =accountService.getAllAccounts();
		 return ResponseEntity.ok(accounts);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountService.deleteAccountById(id);
		return ResponseEntity.ok("Account is Deleted Successfully!!");
		
		
	}
}
