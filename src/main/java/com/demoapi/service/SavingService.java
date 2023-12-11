package com.demoapi.service;

import com.demoapi.dto.DepositServiceInput;
import com.demoapi.dto.DepositServiceOutput;
import com.demoapi.dto.WithdrawServiceInput;
import com.demoapi.dto.WithdrawServiceOutput;
import org.springframework.stereotype.Service;

public interface SavingService {

    DepositServiceOutput deposit (DepositServiceInput depositServiceInput);

    WithdrawServiceOutput withdraw (WithdrawServiceInput withdrawServiceInput);
}
