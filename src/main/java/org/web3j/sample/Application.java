package org.web3j.sample;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
//import org.web3j.sample.contracts.generated.Greeter;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

/**
 * A simple web3j application that demonstrates a number of core features of web3j:
 *
 * <ol>
 *     <li>Connecting to a node on the Ethereum network</li>
 *     <li>Loading an Ethereum wallet file</li>
 *     <li>Sending Ether from one address to another</li>
 *     <li>Deploying a smart contract to the network</li>
 *     <li>Reading a value from the deployed smart contract</li>
 *     <li>Updating a value in the deployed smart contract</li>
 *     <li>Viewing an event logged by the smart contract</li>
 * </ol>
 *
 * <p>To run this demo, you will need to provide:
 *
 * <ol>
 *     <li>Ethereum client (or node) endpoint. The simplest thing to do is
 *     <a href="https://infura.io/register.html">request a free access token from Infura</a></li>
 *     <li>A wallet file. This can be generated using the web3j
 *     <a href="https://docs.web3j.io/command_line.html">command line tools</a></li>
 *     <li>Some Ether. This can be requested from the
 *     <a href="https://www.rinkeby.io/#faucet">Rinkeby Faucet</a></li>
 * </ol>
 *
 * <p>For further background information, refer to the project README.
 */
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        //new Application().run();
        Application app = new Application();

        String amount = "5";
        String toAddress = "0x338a5b100C94e89FEbd3591F154C885D17453302";
        //app.transfer(amount, toAddress);// 直接使用默认钱包向地址转账

        String pwd = "nzlhgnfxl666";//钱包密码
        //钱包文件路径
        String walletPath = "F:/我的坚果云/Master/Code/Blockchain/web3j-4.3.0/web3j-4.3.0/bin/keystore/UTC--2019-07-19T02-18-32.941000000Z--415042fa7b66826a06c6cff4bca2d43b214e50b2.json";
        //app.transfer(amount, pwd, walletPath, toAddress);//指定钱包向用户地址转账

        String url = "https://rinkeby.infura.io/v3/34ada3de9b9e4186b365975ba1843c4f";
        app.transfer(amount, url, pwd, walletPath, toAddress);//特定网络结点，指定钱包向用户地址转账


}



    public void transfer(String amount, String toAddress) throws  Exception {

        Web3j web3j = Web3j.build(new HttpService(
                "https://rinkeby.infura.io/v3/34ada3de9b9e4186b365975ba1843c4f"));  // FIXME: Enter your Infura token here;
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());

        Credentials credentials =
                WalletUtils.loadCredentials(
                        "nzlhgnfxl666",
                        "F:/我的坚果云/Master/Code/Blockchain/web3j-4.3.0/web3j-4.3.0/bin/keystore/UTC--2019-07-19T02-18-32.941000000Z--415042fa7b66826a06c6cff4bca2d43b214e50b2.json");
        log.info("Credentials loaded");

        log.info("Sending ("
                + Convert.fromWei(amount, Convert.Unit.ETHER).toPlainString() + " Ether)");

        BigDecimal num = new BigDecimal(amount);
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                toAddress,  // you can put any address here
                num.multiply(BigDecimal.ONE), Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();
        log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
                + transferReceipt.getTransactionHash());
    }

    public void transfer(String amount, String pwd, String walletPath, String toAddress) throws  Exception {
        Web3j web3j = Web3j.build(new HttpService(
                "https://rinkeby.infura.io/v3/34ada3de9b9e4186b365975ba1843c4f"));  // FIXME: Enter your Infura token here;
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());

        Credentials credentials =
                WalletUtils.loadCredentials(
                        pwd,
                        walletPath);
        log.info("Credentials loaded");

        log.info("Sending ("
                + Convert.fromWei(amount, Convert.Unit.ETHER).toPlainString() + " Ether)");
        BigDecimal num = new BigDecimal(amount);
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                toAddress,  // you can put any address here
                num.multiply(BigDecimal.ONE), Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();
        log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
                + transferReceipt.getTransactionHash());
    }

    public void transfer(String amount, String url, String pwd, String walletPath, String toAddress) throws  Exception {
        Web3j web3j = Web3j.build(new HttpService(
                url));
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());

        Credentials credentials =
                WalletUtils.loadCredentials(
                        pwd,
                        walletPath);
        log.info("Credentials loaded");

        log.info("Sending ("
                + Convert.fromWei(amount, Convert.Unit.ETHER).toPlainString() + " Ether)");
        BigDecimal num = new BigDecimal(amount);
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                toAddress,  // you can put any address here
                num.multiply(BigDecimal.ONE), Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();
        log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
                + transferReceipt.getTransactionHash());
    }

    private void run() throws Exception {

        // We start by creating a new web3j instance to connect to remote nodes on the network.
        // Note: if using web3j Android, use Web3jFactory.build(...
        Web3j web3j = Web3j.build(new HttpService(
                "https://rinkeby.infura.io/v3/34ada3de9b9e4186b365975ba1843c4f"));  // FIXME: Enter your Infura token here;
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());

        // We then need to load our Ethereum wallet file
        // FIXME: Generate a new wallet file using the web3j command line tools https://docs.web3j.io/command_line.html
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "nzlhgnfxl666",
                        "F:/我的坚果云/Master/Code/Blockchain/web3j-4.3.0/web3j-4.3.0/bin/keystore/UTC--2019-07-19T02-18-32.941000000Z--415042fa7b66826a06c6cff4bca2d43b214e50b2.json");
        log.info("Credentials loaded");

        // FIXME: Request some Ether for the Rinkeby test network at https://www.rinkeby.io/#faucet
        log.info("Sending 1 Wei ("
                + Convert.fromWei("2", Convert.Unit.ETHER).toPlainString() + " Ether)");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                "0x338a5b100C94e89FEbd3591F154C885D17453302",  // you can put any address here
                BigDecimal.ONE, Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();
        log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
                + transferReceipt.getTransactionHash());

        // Now lets deploy a smart contract
//        log.info("Deploying smart contract");
//        ContractGasProvider contractGasProvider = new DefaultGasProvider();
//        Greeter contract = Greeter.deploy(
//                web3j,
//                credentials,
//                contractGasProvider,
//                "test"
//                ).send();
//
//        String contractAddress = contract.getContractAddress();
//        log.info("Smart contract deployed to address " + contractAddress);
//        log.info("View contract at https://rinkeby.etherscan.io/address/" + contractAddress);
//
//        log.info("Value stored in remote smart contract: " + contract.greet().send());
//
//        // Lets modify the value in our smart contract
//        TransactionReceipt transactionReceipt = contract.newGreeting("Well hello again").send();
//
//        log.info("New value stored in remote smart contract: " + contract.greet().send());
//
//        // Events enable us to log specific events happening during the execution of our smart
//        // contract to the blockchain. Index events cannot be logged in their entirety.
//        // For Strings and arrays, the hash of values is provided, not the original value.
//        // For further information, refer to https://docs.web3j.io/filters.html#filters-and-events
//        for (Greeter.ModifiedEventResponse event : contract.getModifiedEvents(transactionReceipt)) {
//            log.info("Modify event fired, previous value: " + event.oldGreeting
//                    + ", new value: " + event.newGreeting);
//            log.info("Indexed event previous value: " + Numeric.toHexString(event.oldGreetingIdx)
//                    + ", new value: " + Numeric.toHexString(event.newGreetingIdx));
//        }
    }
}
