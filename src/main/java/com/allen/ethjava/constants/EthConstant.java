package com.allen.ethjava.constants;

import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

/**
 * @author allen
 * @date 2022/11/10 10:25
 */
@Component
public class EthConstant {

    public final static String URL = "https://goerli.infura.io/v3/c61ff59cee814341b92296e70ecc6063";

    public final static Web3j WEB3J =  Web3j.build(new HttpService(URL));
}
