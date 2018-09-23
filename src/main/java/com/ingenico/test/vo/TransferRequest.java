package com.ingenico.test.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferRequest {

    @NotNull
    private String fromAccount;
    @NotNull
    private String toAccount;
    @NotNull
    private BigDecimal amount;
}
