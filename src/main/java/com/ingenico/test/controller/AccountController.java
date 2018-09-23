package com.ingenico.test.controller;

import com.ingenico.test.service.AccountService;
import com.ingenico.test.vo.Account;
import com.ingenico.test.vo.Response;
import com.ingenico.test.vo.ResponseStatus;
import com.ingenico.test.vo.TransferRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("account")
@Api(value = "Account Controller", description = "Services related to Account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "Service to create account with basic details", response = Response.class)
    @PostMapping("/create")
    public Response createAccount(@Valid @RequestBody Account account) {
        return accountService.createAccount(account);

    }

    @ApiOperation(value = "Service to transfer amount from one account to another account", response = Response.class)
    @PostMapping("/transfer")
    public Response transferAmount(@Valid @RequestBody TransferRequest transferRequest) {
      return accountService.transferAmount(transferRequest);
    }

    @GetMapping("/get")
    @ApiOperation(value = "Service to get the list of all the available accounts", response = Iterable.class)
    public Iterable<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }


}
