package com.allen.ethjava.account;

import com.allen.ethjava.constants.EthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import java.math.BigInteger;

/**
 * @author allen
 * @date 2022/10/25 16:38
 */
@Service
@Slf4j
public class BalanceService {

    public BigInteger getGasPrice(){
        try {
            EthGasPrice gasPrice = EthConstant.WEB3J.ethGasPrice().sendAsync().get();
            return gasPrice.getGasPrice();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return BigInteger.ZERO;
    }

    public BigInteger getBalance(String address){
        try {
            DefaultBlockParameterName defaultBlockParameterName = DefaultBlockParameterName.fromString(DefaultBlockParameterName.LATEST.name());
            EthGetBalance ethGetBalance = EthConstant.WEB3J.ethGetBalance(address, defaultBlockParameterName).sendAsync().get();
            return ethGetBalance.getBalance();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return BigInteger.ZERO;
    }




}
