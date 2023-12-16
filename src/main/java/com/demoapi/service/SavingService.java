package com.demoapi.service;

import com.demoapi.dto.DepositInput;
import com.demoapi.dto.DepositOutput;
import com.demoapi.dto.WithdrawInput;
import com.demoapi.dto.WithdrawOutput;

public interface SavingService {

    DepositOutput deposit (DepositInput depositInput);

    WithdrawOutput withdraw (WithdrawInput withdrawInput);
}
