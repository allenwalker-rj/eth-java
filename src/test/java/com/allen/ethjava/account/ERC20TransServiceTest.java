package com.allen.ethjava.account;

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

    @Test
    void transferERC20Token() {
        String from = "0xce8C1E1b11e06FaE762f6E2b5264961C0C7A6a48";
        String to = "0x551B1AE3AA1d19e7976F5Fd8D69B412D595eE9C4";
        String privateKey = "7d672dd3c7e63a856e11a114464448f3f320e52d22e5268c23e485d11a25119a";
        String contractAddress = "0x326C977E6efc84E512bB9C30f76E30c160eD06FB";
        BigInteger value = BigInteger.valueOf(1);
        String s = erc20TransService.transferERC20Token(from, to, value, privateKey, contractAddress);
        log.info(s);
    }
}