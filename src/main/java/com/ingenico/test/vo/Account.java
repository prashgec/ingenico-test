package com.ingenico.test.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    private String name;
    @Column
    private BigDecimal balance;

}
