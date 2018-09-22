package com.ingenico.test.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {

    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
}
