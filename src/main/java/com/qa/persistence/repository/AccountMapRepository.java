package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;
import com.qa.persistence.util.JSONUtil;

public class AccountMapRepository implements AccountRepository{
	
	public Map<Long, Account> accounts = new HashMap<>();
	JSONUtil json = new JSONUtil();

	public String getAllAccounts() {
		String output = "";
//		accounts.values().stream()
		for (int i = 0; i < accounts.size(); i++) {
			output += json.getJSONForObject(accounts.get(i));
		}
		return output;	
	}

	public String createAccount(String jsonString) {
		Account account = json.getObjectForJSON(jsonString, Account.class);
		accounts.put((long) accounts.size(), account);
		return null;
	}

	public String deleteAccount(Long id) {
		accounts.remove(id);
		return null;  
	}

	public String updateAccount(Long id, String jsonString) {
		Account account = json.getObjectForJSON(jsonString, Account.class);
		accounts.replace(id, account);
		return null;
	}

	public Object countFirstName(String string) {
		return null;
	}

}
