package com.demoapi.controller;

import com.demoapi.dto.*;
import com.demoapi.service.SavingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingController {
    public static String depositSuccess = "帳號:account存款成功，餘額:";
    public static String depositFail = "帳號:account不存在";
    public static String withdrawSuccess = "帳號:account取款成功，餘額:";
    public static String withdrawFailExist = "帳號:account不存在";
    public static String withdrawFailSufficient = "帳號:account取款失敗，餘額不足";

    @Autowired
    private SavingService savingService;

    // 存款api deposit
    @PostMapping("/deposit")
    public String deposit(@RequestBody @Valid DepositRequest depositRequest) {
        DepositServiceInput input = new DepositServiceInput();
        input.setAccount(depositRequest.getAccount());
        input.setAmount(depositRequest.getAmount());
        DepositServiceOutput output = savingService.deposit(input);
        if (output.isSuccess()) {
            return depositSuccess.replace("account", output.getAccount())
                    + output.getBalance();
        } else {
            return depositFail.replace("account", output.getAccount());
        }
    }

    // 提款api withdraw
    @PostMapping("/withdraw")
    public String withdraw(@RequestBody @Valid WithdrawRequest withdrawRequest) {
        WithdrawServiceInput input = new WithdrawServiceInput();
        input.setAccount(withdrawRequest.getAccount());
        input.setAmount(withdrawRequest.getAmount());
        WithdrawServiceOutput output = savingService.withdraw(input);
        if (output.isExist() && output.isSuccess()) {
           return withdrawSuccess.replace("account", output.getAccount())
                   + output.getBalance();
        } else if (!output.isExist()) {
            return withdrawFailExist.replace("account", output.getAccount());
        } else {
            return withdrawFailSufficient.replace("account", output.getAccount());
        }
    }
}
