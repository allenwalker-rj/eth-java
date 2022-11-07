package com.allen.ethjava.account;

import com.allen.ethjava.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

/**
 * @author allen
 * @date 2022/10/31 17:46
 */
@Service
@Slf4j
public class ERC20TransService {

    @Autowired
    private Utils utils;

    /**
     *
     * @param from 转账账号
     * @param to 收账账号
     * @param value 额度
     * @param privateKey 转账账号私钥
     * @param contractAddress 合约地址
     * @return 交易hash 失败返回null
     */
    public String transferERC20Token(String from,
                                     String to,
                                     BigInteger value,
                                     String privateKey,
                                     String contractAddress) {
        //加载转账所需的凭证，用私钥
        Credentials credentials = Credentials.create(privateKey);
        //获取nonce，交易笔数
        BigInteger nonce = getNonce(from);
        //get gasPrice
//        BigInteger gasPrice = getGasPrice();
        BigInteger gasPrice = BigInteger.valueOf(10);
//        BigInteger gasLimit = Contract.GAS_LIMIT;
        BigInteger gasLimit = BigInteger.valueOf(2100);
        //创建RawTransaction交易对象
        Function function = tranFunction(to, value);

        String encodedFunction = FunctionEncoder.encode(function);

        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                contractAddress, encodedFunction);

        //签名Transaction，这里要对交易做签名
        byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);

        String hexValue = Numeric.toHexString(signMessage);
        //发送交易
        EthSendTransaction ethSendTransaction = sendTransaction(hexValue);

        if (ethSendTransaction != null){
            return ethSendTransaction.getTransactionHash();
        }

        return null;
    }

    /**
     * 获取nonce
     * @param from 转账地址
     * @return BigInteger类型nonce
     */
    private BigInteger getNonce(String from) {
        EthGetTransactionCount transactionCount;
        BigInteger nonce = null;
        try {
            Web3j web3j = utils.init();
            transactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).sendAsync().get();
            nonce = transactionCount.getTransactionCount();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
        return nonce;
    }


    /**
     * 获取gas
     * @return BigInteger 当前gas
     */
    private BigInteger getGasPrice() {
        BigInteger gas = null;
        try {
            Web3j web3j = utils.init();
            EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
            if (ethGasPrice != null){
                gas = ethGasPrice.getGasPrice();
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
        return gas;
    }



    /**
     * 合约方法
     * @param to 收账地址
     * @param value 转账额
     * @return Function
     */
    private Function tranFunction(String to, BigInteger value) {
        Function function = null;
        try {
            function = new Function(
                    "transfer",
                    Arrays.asList(new Address(to), new Uint256(value)),
                    Collections.singletonList(new TypeReference<Type>() {
                    }));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return function;
    }

    /**
     * 发送交易
     * @param hexValue hex
     * @return 交易
     */
    private EthSendTransaction sendTransaction(String hexValue) {
        EthSendTransaction transaction = null;
        try {
            Web3j web3j = utils.init();
            transaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
            if (transaction.hasError()){
                log.error(transaction.getError().getMessage());
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
        return transaction;
    }
}
