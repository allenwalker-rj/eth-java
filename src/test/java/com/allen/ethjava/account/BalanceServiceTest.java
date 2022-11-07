package com.allen.ethjava.account;

import com.allen.ethjava.config.EthConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@SpringBootTest
@Slf4j
class BalanceServiceTest {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private EthConfig config;
    @Test
    void getGasPrice() {
        BigInteger gasPrice = balanceService.getGasPrice();
        log.info("gasPrice:{}",gasPrice);
    }

    @Test
    void getBalance(){
        log.info(config.toString());
//        BigInteger blockNumber = BigInteger.valueOf(2156740);
        BigInteger balance = balanceService.getBalance(config.getAddress1());
        log.info("balance:{}",balance);
        BigDecimal fromWei = Convert.fromWei(String.valueOf(balance), Convert.Unit.ETHER);
        log.info("fromWei:{}",fromWei);
    }
}