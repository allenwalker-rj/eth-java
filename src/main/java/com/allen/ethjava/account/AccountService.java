package com.allen.ethjava.account;

import com.allen.ethjava.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;

import java.util.Collections;
import java.util.List;

/**
 * @author allen
 * @date 2022/10/20 14:16
 */
@Service
@Slf4j
public class AccountService {

    @Autowired
    private Utils utils;

    public List<String> createAccount(){
        Web3j web3j = utils.init();
        try {
            EthAccounts accounts = web3j.ethAccounts().sendAsync().get();
            return accounts.getAccounts();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  Collections.emptyList();
    }

    public void getAccount(){
        Web3j web3j = utils.init();
        try {
            web3j.ethAccounts().sendAsync().get();

        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
}
