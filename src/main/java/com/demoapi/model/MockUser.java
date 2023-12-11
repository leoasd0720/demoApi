package com.demoapi.model;

import java.util.HashMap;

public class MockUser {
    private HashMap<String, Integer> account = new HashMap<>();

    public MockUser() {
        HashMap<String, Integer> account = new HashMap<>();
        account.put("A123", 2000);
        account.put("B456", 100);
        account.put("C789", 0);
        this.account = account;
    }

    public HashMap<String, Integer> getAccount() {
        return account;
    }

    public void setAccount(HashMap<String, Integer> account) {
        this.account = account;
    }
}
