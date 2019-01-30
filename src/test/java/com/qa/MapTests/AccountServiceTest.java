package com.qa.MapTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.persistence.util.JSONUtil;

public class AccountServiceTest {
	AccountMapRepository repo;
	JSONUtil json;

	@Before
	public void setup() {
		repo = new AccountMapRepository();
		json = new JSONUtil();
	}
	
	@Test
	public void testJsonToObj() {
		String jsonTest = "{\"firstName\":\"John\", \"lastName\":\"Smith\", \"accountNumber\":1234}";
		Account account = json.getObjectForJSON(jsonTest, Account.class);
		assertEquals("Names did not match", "John", account.getFirstName()); 
	}
	
	@Test
	public void testObjToJson() {
		Account account = new Account("John", "Smith", 1234);
		String jsonTest = json.getJSONForObject(account);
		assertEquals("Json not correct", "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"accountNumber\":1234}", jsonTest);
	} 
	 
	@Test
	public void addAccountTest() {
		int mapSize = repo.accounts.size();
		Account account = new Account("Tom", "Smith", 1111);
		repo.createAccount(json.getJSONForObject(account));
		assertEquals("Map size did not increase by 1.", mapSize + 1, repo.accounts.size());                                                                                         
	}
	
	@Test
	public void add2AccountTest() {
		int mapSize = repo.accounts.size();
		Account account = new Account("Tom", "Smith", 1111);
		Account account2 = new Account("Jom", "Jith", 1112);
		repo.createAccount(json.getJSONForObject(account));
		repo.createAccount(json.getJSONForObject(account2));
		assertEquals("Map size did not increase by 2.", mapSize + 2, repo.accounts.size());
	}

	@Test
	public void removeAccountTest() {
		Account account = new Account("Tom", "Smith", 1111);
		repo.createAccount(json.getJSONForObject(account));
		int mapSize = repo.accounts.size();
		repo.deleteAccount((long) mapSize - 1);
		assertEquals("Map size did not decrease by 1.", mapSize - 1, repo.accounts.size());
	}
	
	@Test
	public void remove2AccountTest() {
		Account account = new Account("Tom", "Smith", 1111);
		Account account2 = new Account("Jom", "Jith", 1112);
		repo.createAccount(json.getJSONForObject(account));
		repo.createAccount(json.getJSONForObject(account2));
		int mapSize = repo.accounts.size();
		repo.deleteAccount((long) mapSize - 1);
		repo.deleteAccount((long) mapSize - 2);
		assertEquals("Map size did not decrease by 2.", mapSize - 2, repo.accounts.size());
	}
	
	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		Account account = new Account("Tom", "Smith", 1111);
		Account account2 = new Account("Jom", "Jith", 1112);
		repo.createAccount(json.getJSONForObject(account));
		repo.createAccount(json.getJSONForObject(account2));
		int mapSize = repo.accounts.size();
		repo.deleteAccount((long) mapSize - 1);
		repo.deleteAccount((long) mapSize - 2);
		repo.deleteAccount((long) mapSize - 3);
		assertEquals("Map size did not decrease by 2.", mapSize -2, repo.accounts.size());
		
	}
	
	@Test
	public void testFirstNameCount() {
		Account account1 = new Account("Tom", "Smith", 1234);
		Account account2 = new Account("Jom", "Jmith", 1235);
		Account account3 = new Account("Jom", "Zmith", 1236);
		repo.createAccount(json.getJSONForObject(account1));
		repo.createAccount(json.getJSONForObject(account2));
		repo.createAccount(json.getJSONForObject(account3));
		assertEquals("Incorrect number of Jom first names.", 2, repo.countFirstName("Jom"));
		
	}
	
	@Test
	public void accountConversionToJSONTestWithEmptyMap() {
	
	}
	
	@Test
	public void accountConversionToJSONTestEmptyMapWithConversion() {
	
	}

	@Test
	public void accountConversionToJSONTest() {
		
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		
	}
	
	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		
	}

	@Test
	public void getCountForFirstNamesInAccountWhenMult() {
		
	}

}
