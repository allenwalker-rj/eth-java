## eth-java
### 简介
1. 本项目使用`Java`的方式实现以太坊API调用
2. 本项目需要使用 `org.web3j` jar 包已经导入项目，具体请查看 `pom.xml`
3. 本项目使用的测试网段为 `Goerli Testnet`, 可根据自身情况选择测试网段
### 使用
#### 开发环境
* JDK 1.8+
* Maven 3.6.3+
* Gradle 7.5.1

#### 配置文件
* address1: 账户1地址 (填写自己的账户地址即可)
* address2: 账户2地址 (填写自己另外的账户地址即可)
* privateKey1: 账户1私钥 (填写自己的账户私钥即可)
* privateKey2: 账户2私钥 (填写自己另外的账户私钥即可)
* contractAddress: 合约地址 ([合约详情](https://goerli.etherscan.io/token/0x326c977e6efc84e512bb9c30f76e30c160ed06fb?a=0xce8C1E1b11e06FaE762f6E2b5264961C0C7A6a48))


### 一些注意点
* 因为ETH的特殊性，很多官方网站或者工具网站，都需要梯子，请学习前准备好必要工具 (真的很重要)
* 本项目使用 `infura` 做连接工具, 项目虽然有提供 `API KEY`, 但是为了更好的了解运行机制, 还是建议大家自行注册一个使用, 可自行搜索使用教程 [infura官方地址](https://infura.io/)
* 获取测试币的详细教程 [Goerli测试币获取](https://hackmd.io/@m061i6/HJQQ4G5-j)，需梯子和一个推特账号