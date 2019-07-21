# CrowdChain
The location preserve anonymous payment system based on permissioned blockchain in CrowdOS

[TOC]
## Pay Bonus By Blockchain(for developer)

### Create Wallet

The command line tools can be obtained as a zipfile/tarball from the [releases](
https://github.com/web3j/web3j/releases/tag/4.3.0) page of the project repository, under the Downloads section, or for OS X users via Homebrew, or for Arch linux users via the [AUR](
https://aur.archlinux.org/packages/web3j/).

 [more info](https://docs.web3j.io/command_line.html).

#### Windows
Use PlwerShell input the commond.
```Shell
$ ./web3j
```

To generate a new Ethereum wallet:
```shell
$ ./web3j wallet create

//then type the password
Please enter a wallet file password:
Please re-enter the password:
Please enter a destination directory location [C:\Users\Godfather\AppData\Roaming\Ethereum\testnet\keystore]: keystore
Creating directory: keystore ...complete
```

To update the password for an existing wallet:
```shell
$ ./web3j wallet update <walletfile>
```

### Get Wallet Address
Login [MyEtherWallet](https://www.myetherwallet.com/interface) using your wallet file and passowrd, then you will find your address.

### Deployment Project
#### Maven
**Java 8**
```xml
<dependency>
  <groupId>org.web3j</groupId>
  <artifactId>core</artifactId>
  <version>4.3.0</version>
</dependency>
```
#### Gradle

**Java 8**
```java
compile ('org.web3j:core:3.4.0')
```
The source code in [Github](https://github.com/JackKing-defier/CrowdChain).

### Signing Infura Service 

The Infura service by ConsenSys, provides Ethereum clients running in the cloud, so you don’t have to run one yourself to work with Ethereum.

When you sign up to the service you are provided with a token you can use to connect to the relevant Ethereum network:

Main Ethereum Network:
https://mainnet.infura.io/<your-token>

Test Ethereum Network (Rinkeby):
https://rinkeby.infura.io/<your-token>

**For example, my token is:**
https://rinkeby.infura.io/v3/34ada3de9b9e4186b365975ba1843c4f

You can create the client just like the regular HTTPClient:
```java
Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/<your-token>"));
Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
System.out.println(web3ClientVersion.getWeb3ClientVersion());
```
### Ethereum Testnets

There are a number of dedicated test networks in Ethereum, which are supported by various clients.
+ Rinkeby (Geth only)
+ Kovan (Parity only)
+ Ropsten (Geth and Parity)

For development, its recommended you use the Rinkeby or Kovan test networks. This is because they use a Proof of Authority (PoA) consensus mechanism, ensuring transactions and blocks are created in a consistent and timely manner. The Ropsten testnet, although closest to the Mainnet as it uses Proof of Work (PoW) consensus, has been subject to attacks in the past and tends to be more problematic for developers.

You can request Ether for the Rinkeby testnet via the Rinkeby Crypto Faucet, available at https://www.rinkeby.io/.


### Pay Bonus 
I edited three transfer functions with different parameters.

```java
Application app = new Application();
String amount = "5";
String toAddress = 
"0x338a5b100C94e89FEbd3591F154C885D17453302";
//app.transfer(amount, toAddress);// 直接使用默认钱包向地址转账
String pwd = "54188cnm";//钱包密码
//钱包文件路径
String walletPath = "F:/我的坚果云/Master/Code/Blockchain/web3j-4.3.0/web3j-4.3.0/bin/keystore/UTC--2019-07-19T02-18-32.941000000Z--415042fa7b66826a06c6cff4bca2d43b214e50b2.json";
//app.transfer(amount, pwd, walletPath, toAddress);//指定钱包向用户地址转账
String url = "https://rinkeby.infura.io/v3/34ada3de9b9e4186b365975ba1843c4f";
app.transfer(amount, url, pwd, walletPath, toAddress);
//特定网络结点，指定钱包向用户地址转账

```
The example gist of transfer():
```java
public void transfer(String amount, String url, String pwd, String walletPath, String toAddress) throws  Exception {    
        Web3j web3j = Web3j.build(new HttpService(url));
        log.info("Connected to Ethereum client version: "           
                  + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        Credentials credentials =            
                 WalletUtils.loadCredentials( pwd,  walletPath);   
        log.info("Credentials loaded");   
        log.info("Sending ("            
                + Convert.fromWei(amount,  Convert.Unit.ETHER).toPlainString() + " Ether)");    
        BigDecimal num = new BigDecimal(amount);    
        TransactionReceipt transferReceipt = 
                                  Transfer.sendFunds(           
                                  web3j, credentials,           
                                  toAddress,  // you can put any address here            
                                  num.multiply(BigDecimal.ONE), 
                                  Convert.Unit.WEI)  // 1 wei = 10^-18 Ether           
                                  .send();    
         log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"            
                        + transferReceipt.getTransactionHash());
 }

```


### Check the Balance
You can check your balance in [Rinkeby](https://rinkeby.etherscan.io/).
Just search your wallet address.


## Receive Bonus By Blockchain(for user)

### Create Wallet

The command line tools can be obtained as a zipfile/tarball from the [releases](
https://github.com/web3j/web3j/releases/tag/4.3.0) page of the project repository, under the Downloads section, or for OS X users via Homebrew, or for Arch linux users via the [AUR](
https://aur.archlinux.org/packages/web3j/).

 [more info](https://docs.web3j.io/command_line.html).

#### Windows
Use PlwerShell input the commond.
```Shell
$ ./web3j
```

To generate a new Ethereum wallet:
```shell
$ ./web3j wallet create

//then type the password
Please enter a wallet file password:
Please re-enter the password:
Please enter a destination directory location [C:\Users\Godfather\AppData\Roaming\Ethereum\testnet\keystore]: keystore
Creating directory: keystore ...complete
```

To update the password for an existing wallet:
```shell
$ ./web3j wallet update <walletfile>
```

### Get Wallet Address
Login [MyEtherWallet](https://www.myetherwallet.com/interface) using your wallet file and passowrd, then you will find your address.

### Get Money 
Commit your wallet address when you upload the task data in CrowdOS.

### Check the Balance
You can check your balance in [Rinkeby](https://rinkeby.etherscan.io/).
Just search your wallet address.



