package com.demoapi.service.Impl;

import com.demoapi.dto.DepositServiceInput;
import com.demoapi.dto.DepositServiceOutput;
import com.demoapi.dto.WithdrawServiceInput;
import com.demoapi.dto.WithdrawServiceOutput;
import com.demoapi.model.MockUser;
import com.demoapi.service.SavingService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class SavingServiceImpl implements SavingService {

    @Override
    public DepositServiceOutput deposit(DepositServiceInput depositServiceInput) {
        DepositServiceOutput depositServiceOutput = new DepositServiceOutput();
        String account = null;
        Integer balance = 0;

        // 0.初始化帳戶
        HashMap<String, Integer> accountMap = getAccountMap();
        // 1.檢查帳戶存在
        account = depositServiceInput.getAccount();
        boolean exist = isExist(account, accountMap);
        // 2.執行操作
        if (exist){
            balance = accountMap.get(account) + depositServiceInput.getAmount();
            accountMap.put(account, balance);
        }
        // 3.返回執行結果
        depositServiceOutput.setSuccess(exist);
        depositServiceOutput.setAccount(account);
        depositServiceOutput.setBalance(balance);
        return depositServiceOutput;
    }


    @Override
    public WithdrawServiceOutput withdraw(WithdrawServiceInput withdrawServiceInput) {
        WithdrawServiceOutput withdrawServiceOutput = new WithdrawServiceOutput();
        String account = null;
        Integer balance = 0;
        boolean sufficient = false;

        // 0.初始化帳戶
        HashMap<String, Integer> accountMap = getAccountMap();
        // 1.檢查帳戶存在
        account = withdrawServiceInput.getAccount();
        boolean exist = isExist(account, accountMap);
        // 2.執行操作
        if (exist) {
            sufficient = accountMap.get(account) > withdrawServiceInput.getAmount();
            if (sufficient) {
                balance = accountMap.get(account) - withdrawServiceInput.getAmount();
                accountMap.put(account, balance);
            }
        }
        // 3.返回執行結果
        withdrawServiceOutput.setExist(exist);
        withdrawServiceOutput.setSuccess(sufficient);
        withdrawServiceOutput.setAccount(account);
        withdrawServiceOutput.setBalance(balance);
        return withdrawServiceOutput;
    }

    private HashMap<String, Integer> getAccountMap() {
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
