package com.allen.ethjava.account;

import com.allen.ethjava.config.EthConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TransServiceTest {

    @Autowired
    private  TransService transService;

    @Autowired
    private EthConfig ethConfig;

    @Test
    void ethTrans() {
        String from = ethConfig.getAddress1();
        String to = ethConfig.getAddress2();
        String privateKey = ethConfig.getPrivateKey1();
        // amount的单位是 wei
        BigInteger amount = BigInteger.valueOf(100000000);
        String txHash = transService.ethTrans(from, privateKey, to, amount);
        log.info("txHash : {}", txHash);
    }
}