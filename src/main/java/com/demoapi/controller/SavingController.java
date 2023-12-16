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
    public static String depositSuccess = "帳戶:account存款成功，餘額:balance元。";
    public static String depositFail = "帳戶:account存款失敗。";
    public static String withdrawSuccess = "帳戶:account取款成功，餘額:balance元。";
    public static String withdrawFailExist = "帳戶:account不存在。";
    public static String withdrawFail = "帳戶:account取款失敗，餘額不足。";

    @Autowired
    private SavingService savingService;

    // 存款api deposit
    @PostMapping("/deposit")
    public String deposit(@RequestBody @Valid DepositRequest depositRequest) {
        DepositInput input = new DepositInput();
        String result = "";
        input.setAccount(depositRequest.getAccount());
        input.setAmount(depositRequest.getAmount());
        DepositOutput output = savingService.deposit(input);
        if (output.isSuccess()) {
            result = depositSuccess.replace("account", output.getAccount())
                                   .replace("balance", output.getBalance().toString());
        } else {
            result = depositFail.replace("account", output.getAccount());
        }
        return result;
    }

    // 提款api withdraw
    @PostMapping("/withdraw")
    public String withdraw(@RequestBody @Valid WithdrawRequest withdrawRequest) {
        WithdrawInput input = new WithdrawInput();
        String result = "";
        input.setAccount(withdrawRequest.getAccount());
        input.setAmount(withdrawRequest.getAmount());
        WithdrawOutput output = savingService.withdraw(input);
        if (!output.isExist()) {
            result = withdrawFailExist.replace("account", output.getAccount());
        } else if (!output.isSuccess()) {
            result = withdrawFail.replace("account", output.getAccount());
        }else {
            result = withdrawSuccess.replace("account", output.getAccount())
                                    .replace("balance", output.getBalance().toString());
        }
        return result;
    }
}
