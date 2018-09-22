package com.ingenico.test.service.impl;

import com.ingenico.test.repository.AccountRepository;
import com.ingenico.test.service.AccountService;
import com.ingenico.test.vo.Account;
import com.ingenico.test.vo.Response;
import com.ingenico.test.vo.ResponseStatus;
import com.ingenico.test.vo.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Response createAccount(Account account) {
        if(accountRepository.findById(account.getName()).orElse(null)!=null)
            return new Response(ResponseStatus.ERROR,"Account already available");
        else if(account.getBalance().compareTo(BigDecimal.ZERO)<0)
            return new Response(ResponseStatus.ERROR,"Account cant be opened with negetive balance");
        else
            accountRepository.save(account);
        return new Response(ResponseStatus.SUCCESS,"Account created successfully!");
    }

    @Override
    @Transactional
    public Response transferAmount(TransferRequest transferRequest) {
        synchronized (this)
        {
            Account from = accountRepository.findById(transferRequest.getFromAccount()).orElse(null);
            Account to = accountRepository.findById(transferRequest.getToAccount()).orElse(null);
            if(from==null)
                return new Response(ResponseStatus.ERROR,"From Account not available");
            else if(to==null)
                return new Response(ResponseStatus.ERROR,"to Account not available");
            else if(from.getBalance().compareTo(transferRequest.getAmount())<0)
                return new Response(ResponseStatus.ERROR,"From account doesn't have sufficient funds");
            //Making actual transaction
            BigDecimal fromBalance=from.getBalance();
            BigDecimal toBalance= to.getBalance();
            fromBalance=fromBalance.subtract(transferRequest.getAmount());
            toBalance=toBalance.add(transferRequest.getAmount());
            from.setBalance(fromBalance);
            to.setBalance(toBalance);
            accountRepository.save(from);
            accountRepository.save(to);
            return new Response(ResponseStatus.SUCCESS,"Transaction Successfull!");
        }

    }

    @Override
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }



}
