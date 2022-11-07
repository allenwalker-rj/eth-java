package com.allen.ethjava.account;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BalanceServiceTest {

    @Autowired
    private BalanceService balanceService;

    @Test
    void getGasPrice() {
        BigInteger gasPrice = balanceService.getGasPrice();
        log.info("gasPrice:{}",gasPrice);
    }

    @Test
    void getBalance(){
        String address = "0x455e5aa18469bc6ccef49594645666c587a3a71b";
//        BigInteger blockNumber = BigInteger.valueOf(2156740);
        BigInteger balance = balanceService.getBalance(address);
        log.info("balance:{}",balance);
        BigDecimal fromWei = Convert.fromWei(String.valueOf(balance), Convert.Unit.ETHER);
        log.info("fromWei:{}",fromWei);
    }
}