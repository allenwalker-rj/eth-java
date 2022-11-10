package com.allen.ethjava.account;

import com.allen.ethjava.constants.EthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.EthBlockNumber;

import java.math.BigInteger;

/**
 * @author allen
 * @date 2022/10/25 16:29
 */
@Service
@Slf4j
public class BlockService {

    public BigInteger getLastBlock(){
        try {
            EthBlockNumber ethBlockNumber = EthConstant.WEB3J.ethBlockNumber().sendAsync().get();
            return ethBlockNumber.getBlockNumber();
        }catch ( Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
