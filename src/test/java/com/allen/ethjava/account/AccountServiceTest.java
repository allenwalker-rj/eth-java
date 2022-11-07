package com.allen.ethjava.account;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void createAccount() {
        List<String> accounts = accountService.createAccount();
        log.info("accounts:{}", accounts.toString());
    }
}