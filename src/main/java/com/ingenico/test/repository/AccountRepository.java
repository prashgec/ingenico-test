package com.ingenico.test.repository;

import com.ingenico.test.vo.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,String> {
}
