package com.allen.ethjava.account;

import com.allen.ethjava.config.EthConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest
@Slf4j
class ERC20TransServiceTest {

    @Autowired
    private ERC20TransService erc20TransService;

    @Autowired
    private EthConfig config;

    @Test
    void transferERC20Token() {
        String from = config.getAddress1();
        String to = config.getAddress2();
        String privateKey = config.getPrivateKey1();
        String contractAddress = config.getContractAddress();
        BigInteger value = BigInteger.valueOf(1);
        String s = erc20TransService.transferERC20Token(from, to, value, privateKey, contractAddress);
        log.info(s);
    }
}