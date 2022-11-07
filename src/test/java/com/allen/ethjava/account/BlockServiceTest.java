package com.allen.ethjava.account;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest
@Slf4j
class BlockServiceTest {

    @Autowired
    private BlockService blockService;

    @Test
    void getLastBlock() {
        BigInteger lastBlock = blockService.getLastBlock();
        log.info(lastBlock.toString());

    }
}