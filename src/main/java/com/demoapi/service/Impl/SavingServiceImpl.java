package com.demoapi.service.Impl;

import com.demoapi.dto.DepositInput;
import com.demoapi.dto.DepositOutput;
import com.demoapi.dto.WithdrawInput;
import com.demoapi.dto.WithdrawOutput;
import com.demoapi.model.MockUser;
import com.demoapi.service.SavingService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class SavingServiceImpl implements SavingService {

    @Override
    public DepositOutput deposit(DepositInput depositInput) {
        DepositOutput depositOutput = new DepositOutput();
        String account = null;
        Integer balance = 0;
        boolean succuss = false;

        // 0.初始化帳戶
        HashMap<String, Integer> accountMap = intialAccountMap();
        // 1.檢查帳戶存在
        account = depositInput.getAccount();
        boolean exist = isExist(account, accountMap);
        // 2.執行操作
        if (exist){
            balance = accountMap.get(account) + depositInput.getAmount();
            accountMap.put(account, balance);
            succuss = true;
        }
        // 3.返回執行結果
        depositOutput.setSuccess(succuss);
        depositOutput.setAccount(account);
        depositOutput.setBalance(balance);
        return depositOutput;
    }


    @Override
    public WithdrawOutput withdraw(WithdrawInput withdrawInput) {
        WithdrawOutput withdrawOutput = new WithdrawOutput();
        String account = null;
        Integer balance = 0;
        boolean enough = false;
        boolean succuss = false;

        // 0.初始化帳戶
        HashMap<String, Integer> accountMap = intialAccountMap();
        // 1.檢查帳戶存在
        account = withdrawInput.getAccount();
        boolean exist = isExist(account, accountMap);
        // 2.執行操作
        if (exist) {
            enough = accountMap.get(account) > withdrawInput.getAmount();
            if (enough) {
                balance = accountMap.get(account) - withdrawInput.getAmount();
                accountMap.put(account, balance);
                succuss = true;
            }
        }
        // 3.返回執行結果
        withdrawOutput.setExist(exist);
        withdrawOutput.setSuccess(succuss);
        withdrawOutput.setAccount(account);
        withdrawOutput.setBalance(balance);
        return withdrawOutput;
    }

    private HashMap<String, Integer> intialAccountMap() {
        MockUser mockUser = new MockUser();
        HashMap<String, Integer> accountMap = mockUser.getAccount();
        return accountMap;
    }

    private boolean isExist(String account, HashMap<String, Integer> accountMap) {
        Set accountSet = accountMap.keySet();
        boolean exist = accountSet.contains(account);
        return exist;
    }

}
