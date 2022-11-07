package com.allen.ethjava.account;

import com.allen.ethjava.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
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

    @Autowired
    private Utils utils;

    public BigInteger getGasPrice(){
        Web3j web3j = utils.init();
        try {
            EthGasPrice gasPrice = web3j.ethGasPrice().sendAsync().get();
            return gasPrice.getGasPrice();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return BigInteger.ZERO;
    }

    public BigInteger getBalance(String address){
        Web3j web3j = utils.init();
        try {
            DefaultBlockParameterName defaultBlockParameterName = DefaultBlockParameterName.fromString(DefaultBlockParameterName.LATEST.name());
            EthGetBalance ethGetBalance = web3j.ethGetBalance(address, defaultBlockParameterName).sendAsync().get();

            return ethGetBalance.getBalance();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return BigInteger.ZERO;
    }




}
