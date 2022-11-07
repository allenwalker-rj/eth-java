package com.allen.ethjava.util;

import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

/**
 * @author allen
 * @date 2022/10/20 14:09
 */
@Component
public class Utils {

    private final static String URL = "https://goerli.infura.io/v3/c61ff59cee814341b92296e70ecc6063";

    public Web3j init(){
        return  Web3j.build(new HttpService(URL));
    }
}
