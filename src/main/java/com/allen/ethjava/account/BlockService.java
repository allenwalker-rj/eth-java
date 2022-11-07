package com.allen.ethjava.account;

import com.allen.ethjava.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;

import java.math.BigInteger;

/**
 * @author allen
 * @date 2022/10/25 16:29
 */
@Service
@Slf4j
public class BlockService {

    @Autowired
    private Utils utils;

    public BigInteger getLastBlock(){
        Web3j web3j = utils.init();
        try {
            EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().sendAsync().get();
            BigInteger blockNumber = ethBlockNumber.getBlockNumber();
            return blockNumber;
        }catch ( Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
