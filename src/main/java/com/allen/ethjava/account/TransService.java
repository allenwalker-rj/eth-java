package com.allen.ethjava.account;

import com.allen.ethjava.config.EthConfig;
import com.allen.ethjava.constants.EthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

/**
 * @author allen
 * @date 2022/11/9 10:50
 */
@Service
@Slf4j
public class TransService {

    @Autowired
    private EthConfig ethConfig;

    /**
     * eth 发起普通交易
     * @param from 发起地址
     * @param privateKey 发起地址秘钥
     * @param to 收款地址
     * @param num 发送金额
     * @return 交易Hash
     */
    public String ethTrans(String from, String privateKey, String to, BigInteger num){
        BigInteger transactionCount = getTransactionCount(from);
        String hexValue = createEtherTransaction(transactionCount, privateKey, to, num);
        String txHash = sendRawTransaction(hexValue);
        return txHash;
    }

    public BigInteger getTransactionCount(String address){
        try {
            EthGetTransactionCount ethGetTransactionCount = EthConstant.WEB3J.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger transactionCount = ethGetTransactionCount.getTransactionCount();
            log.info("transactionCount : {}", transactionCount);
            return  transactionCount;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return BigInteger.ZERO;
    }

    public String createEtherTransaction(BigInteger nonce, String privateKey, String to, BigInteger num){
        BigInteger gasPrice = BigInteger.valueOf(50);
//        BigInteger gasLimit = Contract.GAS_LIMIT;
        BigInteger gasLimit = BigInteger.valueOf(2100000);
        RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, num);
        Credentials credentials = Credentials.create(privateKey);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        log.info("hexValue : {}" ,hexValue);
        return hexValue;
    }

    public String sendRawTransaction(String hexValue){
        try {
            EthSendTransaction ethSendTransaction = EthConstant.WEB3J.ethSendRawTransaction(hexValue).sendAsync().get();
            if (ethSendTransaction.hasError()){
                log.info("send raw error : {}"  ,ethSendTransaction.getError().getMessage());
                throw new Exception(ethSendTransaction.getError().getMessage());
            }else {
                String transactionHash = ethSendTransaction.getTransactionHash();
                log.info("send raw transactionHash : {}" , transactionHash);
                return transactionHash;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
