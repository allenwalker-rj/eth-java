package com.allen.ethjava.account;

import com.allen.ethjava.constants.EthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public List<String> createAccount(){
        try {
            EthAccounts accounts = EthConstant.WEB3J.ethAccounts().sendAsync().get();
            return accounts.getAccounts();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  Collections.emptyList();
    }

    public void getAccount(){
        try {
            EthConstant.WEB3J.ethAccounts().sendAsync().get();

        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
}
