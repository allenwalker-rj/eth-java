package com.allen.ethjava.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author allen
 * @date 2022/11/7 14:24
 */
@Component
@ConfigurationProperties(prefix = "eth")
@Data
public class EthConfig {

    private String address1;

    private String address2;

    private String privateKey1;

    private String privateKey2;

    private String contractAddress;

}
