package com.ingenico.test.service;

import com.ingenico.test.vo.Account;
import com.ingenico.test.vo.Response;
import com.ingenico.test.vo.TransferRequest;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    public Response createAccount(Account account);

    public Response transferAmount(TransferRequest transferRequest);

    public Iterable<Account> getAllAccounts();

}
