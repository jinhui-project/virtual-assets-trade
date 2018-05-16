package com.jinhui.api.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinhui.common.entity.po.FundTransfer;
import com.jinhui.common.entity.po.Wallet;
import com.jinhui.common.entity.po.WalletTx;
import com.jinhui.common.mapper.FundTransferMapper;
import com.jinhui.common.mapper.WalletMapper;
import com.jinhui.common.mapper.WalletTxMapper;
import com.jinhui.common.utils.BtcApiUtils;
import com.jinhui.common.utils.CollectUtils;
import com.jinhui.common.utils.EtherscanApiUtils;
import com.jinhui.common.utils.UnixStampUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

@Component
public class QueryWallet {

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private WalletTxMapper walletTxMapper;

    @Autowired
    private FundTransferMapper fundTransferMapper;


    /**
     * 比特币单位:
     * <p>
     * 1比特币（Bitcoins，BTC）
     * 0.01比特分（Bitcent，cBTC）
     * 0.001毫比特（Milli-Bitcoins，mBTC）
     * 0.000001微比特（Micro-Bitcoins，μBTC或uBTC）
     * 0.00000001聪（satoshi）（基本单位）
     * 1 bitcoin (BTC) = 1000 millibitcoins (mBTC) = 1 million microbitcoins (uBTC) = 100 million Satoshi
     */

    private final static BigDecimal oneHundredMillion = new BigDecimal("100000000");

    /**
     * 以太币单位
     * Unit	Wei Value	Wei
     * wei	1	1 wei
     * Kwei (babbage)	1e3 wei	1,000
     * Mwei (lovelace)	1e6 wei	1,000,000
     * Gwei (shannon)	1e9 wei	1,000,000,000
     * microether (szabo)	1e12 wei	1,000,000,000,000
     * milliether (finney)	1e15 wei	1,000,000,000,000,000
     * ether	1e18 wei	1,000,000,000,000,000,000
     */
    private final static BigDecimal wei18 = new BigDecimal("1000000000000000000");

    public void queryWalletBalance() {

        // 把平台账户的地址信息同步到钱包表

        //根据地址查询余额，并更新钱包的余额
        List<Wallet> wallets = walletMapper.selectAll();

        for (Wallet wallet : wallets) {
            if (wallet.getType().equals("btc")) {
                queryBtcBalance(wallet.getType(), wallet.getAddress());
            }
            if (wallet.getType().equals("eth")) {
                queryEthBalance(wallet.getType(), wallet.getAddress());
            }
        }

    }

    public void queryWalletTx() {

        //根据地址查询出交易记录，增量更新数据库的数据
        List<Wallet> wallets = walletMapper.selectAll();

        for (Wallet wallet : wallets) {
            if (wallet.getType().equals("btc")) {
//                queryBtcTx2(wallet.getWalletName(), wallet.getType(), wallet.getAddress());

//                queryUserBtcTx(wallet.getWalletName(), wallet.getType(), wallet.getAddress());

            }
            if (wallet.getType().equals("eth")) {
                queryEthTx(wallet.getWalletName(), wallet.getType(), wallet.getAddress());
            }
        }


    }


    public void queryUserBtcTx(String name, String type, String address) {

        //查询资金充值记录
//        List<FundTransfer> fundTransfers = fundTransferMapper.selectAll();

        FundTransfer fundTransfer1 = new FundTransfer();
        fundTransfer1.setTransferInAccount("3LZqotJwnEDtyK81CLGKS4Vyd2Ask8BpnX");

        List<FundTransfer> fundTransfers = Arrays.asList(fundTransfer1);
        for (FundTransfer fundTransfer : fundTransfers) {


            String transferInAccount = fundTransfer.getTransferInAccount();

            String json = BtcApiUtils.getTransactions(transferInAccount, 1);

            //先解析出总页数
            JSONObject jsonObject = JSON.parseObject(json);
            String err_no = jsonObject.getString("err_no");

            if (!err_no.equals("0")) {

                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {

                }

                continue;
            }

            JSONObject data = jsonObject.getJSONObject("data");
            int totalCount = data.getInteger("total_count");
            int pageSize = data.getInteger("pagesize");

            int m = totalCount % pageSize;
            int totalPage;
            if (m > 0) {
                totalPage = totalCount / pageSize + 1;
            } else {
                totalPage = totalCount / pageSize;
            }

            System.out.println("totalPage:" + totalPage);
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {

            }
            //得到全部页数
            for (int i = 1; i <= totalPage; i++) {
                System.out.println("i:" + i);
                String jsonI = BtcApiUtils.getTransactions(transferInAccount, i);
                System.out.println("jsonI:" + jsonI);
                JSONObject jsonObjectI = JSON.parseObject(jsonI);
                JSONObject dataI = jsonObjectI.getJSONObject("data");
                JSONArray list = dataI.getJSONArray("list");
                List<JSONObject> jsonObjects = list.toJavaList(JSONObject.class);
                for (JSONObject object : jsonObjects) {

                    Integer confirmations = object.getInteger("confirmations");
                    long blockTime = object.getLong("block_time");
                    String hash = object.getString("hash");

                    //检查是否已存在
                    int count = walletTxMapper.selectByHashCount(hash);
                    if (count != 0) {
                        System.out.println("已经存在");
                        continue;
                    }

                    //可能会有多个输出地址，每条输出保存一条记录
                    JSONArray outputs = object.getJSONArray("outputs");
                    List<JSONObject> outputsObjects = outputs.toJavaList(JSONObject.class);
                    for (JSONObject object2 : outputsObjects) {
                        String toAddresses = object2.getString("addresses");

                        System.out.println("toAddresses:" + toAddresses);

                        if (toAddresses.equals(address)) {
                            BigDecimal value = object2.getBigDecimal("value");
                            WalletTx tx = new WalletTx();
                            tx.setWalletName(name);
                            tx.setWalletAddress(address);
                            tx.setType(type);
                            tx.setBlockTime(UnixStampUtils.unixStamp2Date(blockTime));
                            tx.setCreateTime(new Date());
                            tx.setConfirmations(confirmations);
                            tx.setFromAddress(toAddresses);
                            tx.setToAddress(address);
                            tx.setTxHash(hash);
//                            tx.setOutAmount(value);
                            tx.setInputAmount(value);
                            tx.setTransUnit("Satoshi");
                            tx.setReceiptAmount(value);
                            tx.setBussType("input");
                            walletTxMapper.insert(tx);
                        }

                    }

                }

                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }


    }


    public void confirm() {

        //验证交易记录计算出来的余额和当前余额是否相等

    }


    /**
     * 只记录转出的交易记录
     *
     * @param name
     * @param type
     * @param address
     */
    private void queryBtcTx2(String name, String type, String address) {


        String json = BtcApiUtils.getTransactions(address, 1);
        //先解析出总页数
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject data = jsonObject.getJSONObject("data");
        int totalCount = data.getInteger("total_count");
        int pageSize = data.getInteger("pagesize");

        int m = totalCount % pageSize;
        int totalPage;
        if (m > 0) {
            totalPage = totalCount / pageSize + 1;
        } else {
            totalPage = totalCount / pageSize;
        }

        System.out.println("totalPage:" + totalPage);
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {

        }
        //得到全部页数
        for (int i = 1; i <= totalPage; i++) {
            System.out.println("i:" + i);
            String jsonI = BtcApiUtils.getTransactions(address, i);

            JSONObject jsonObjectI = JSON.parseObject(jsonI);
            JSONObject dataI = jsonObjectI.getJSONObject("data");
            JSONArray list = dataI.getJSONArray("list");
            List<JSONObject> jsonObjects = list.toJavaList(JSONObject.class);
            for (JSONObject object : jsonObjects) {

                Integer confirmations = object.getInteger("confirmations");
                long blockTime = object.getLong("block_time");
//                BigDecimal fee = object.getBigDecimal("fee");
                String hash = object.getString("hash");
//                BigDecimal inputsValue = object.getBigDecimal("inputs_value");
//                BigDecimal outputsValue = object.getBigDecimal("outputs_value");
                BigDecimal balanceDiff = object.getBigDecimal("balance_diff");

                //检查是否已存在
                int count = walletTxMapper.selectByHashCount(hash);
                if (count != 0) {
                    continue;
                }

                //可能会有多个输出地址，每条输出保存一条记录
                JSONArray outputs = object.getJSONArray("outputs");
                List<JSONObject> outputsObjects = outputs.toJavaList(JSONObject.class);
                for (JSONObject object2 : outputsObjects) {
                    String toAddresses = object2.getString("addresses");
                    BigDecimal value = object2.getBigDecimal("value");
                    WalletTx tx = new WalletTx();
                    tx.setWalletName(name);
                    tx.setWalletAddress(address);
                    tx.setType(type);
                    tx.setBlockTime(UnixStampUtils.unixStamp2Date(blockTime));
                    tx.setCreateTime(new Date());
                    tx.setConfirmations(confirmations);
                    tx.setToAddress(toAddresses);
                    tx.setTxHash(hash);
//                    tx.setFeeAmount(fee);
//                    tx.setInputAmount(inputsValue);
                    tx.setOutAmount(value);
                    tx.setTransUnit("Satoshi");
                    tx.setReceiptAmount(value);
                    tx.setBussType("out");
                    walletTxMapper.insert(tx);

                }

            }

            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * private void queryBtcTx(String name, String type, String address) {
     * <p>
     * <p>
     * String json = BtcApiUtils.getTransactions(address, 1);
     * //先解析出总页数
     * JSONObject jsonObject = JSON.parseObject(json);
     * JSONObject data = jsonObject.getJSONObject("data");
     * int totalCount = data.getInteger("total_count");
     * int pageSize = data.getInteger("pagesize");
     * <p>
     * int m = totalCount % pageSize;
     * int totalPage;
     * if (m > 0) {
     * totalPage = totalCount / pageSize + 1;
     * } else {
     * totalPage = totalCount / pageSize;
     * }
     * <p>
     * System.out.println("totalPage:" + totalPage);
     * try {
     * Thread.sleep(5 * 1000);
     * } catch (InterruptedException e) {
     * <p>
     * }
     * //得到全部页数
     * for (int i = 1; i <= totalPage; i++) {
     * System.out.println("i:" + i);
     * String jsonI = BtcApiUtils.getTransactions(address, i);
     * <p>
     * JSONObject jsonObjectI = JSON.parseObject(jsonI);
     * JSONObject dataI = jsonObjectI.getJSONObject("data");
     * JSONArray list = dataI.getJSONArray("list");
     * List<JSONObject> jsonObjects = list.toJavaList(JSONObject.class);
     * for (JSONObject object : jsonObjects) {
     * <p>
     * Integer confirmations = object.getInteger("confirmations");
     * long blockTime = object.getLong("block_time");
     * BigDecimal fee = object.getBigDecimal("fee");
     * String hash = object.getString("hash");
     * BigDecimal inputsValue = object.getBigDecimal("inputs_value");
     * BigDecimal outputsValue = object.getBigDecimal("outputs_value");
     * BigDecimal balanceDiff = object.getBigDecimal("balance_diff");
     * String bussType = null;
     * <p>
     * //可能会有多个输入地址,也可能没有输入地址
     * JSONArray inputs = object.getJSONArray("inputs");
     * List<JSONObject> objects = inputs.toJavaList(JSONObject.class);
     * List fromAddressesList = new ArrayList();
     * for (JSONObject object1 : objects) {
     * String fromAddresses = object1.getString("prev_addresses");
     * fromAddressesList.add(fromAddresses);
     * }
     * String formatList = CollectUtils.formatList(fromAddressesList, ",");
     * <p>
     * //可能会有多个输出地址
     * JSONArray outputs = object.getJSONArray("outputs");
     * List<JSONObject> outputsObjects = outputs.toJavaList(JSONObject.class);
     * List toAddressesList = new ArrayList();
     * for (JSONObject object2 : outputsObjects) {
     * String toAddresses = object2.getString("addresses");
     * toAddressesList.add(toAddresses);
     * <p>
     * }
     * String toFormatList = CollectUtils.formatList(toAddressesList, ",");
     * <p>
     * WalletTx tx = new WalletTx();
     * tx.setWalletName(name);
     * tx.setWalletAddress(address);
     * tx.setType(type);
     * tx.setBlockTime(UnixStampUtils.unixStamp2Date(blockTime));
     * tx.setCreateTime(new Date());
     * tx.setConfirmations(confirmations);
     * tx.setFromAddress(formatList);
     * tx.setToAddress(toFormatList);
     * tx.setTxHash(hash);
     * tx.setFeeAmount(fee);
     * tx.setInputAmount(inputsValue);
     * tx.setOutAmount(outputsValue);
     * tx.setTransUnit("Satoshi");
     * tx.setReceiptAmount(balanceDiff.abs());
     * <p>
     * //                if (formatList.contains(address)) {
     * bussType = "out";
     * //                } else {
     * //                    bussType = "input";
     * //                }
     * <p>
     * tx.setBussType(bussType);
     * <p>
     * <p>
     * //检查是否已存在
     * WalletTx walletTx = walletTxMapper.selectByHash(hash);
     * if (walletTx == null) {
     * walletTxMapper.insert(tx);
     * }
     * <p>
     * }
     * <p>
     * try {
     * Thread.sleep(2 * 1000);
     * } catch (InterruptedException e) {
     * e.printStackTrace();
     * }
     * }
     * <p>
     * <p>
     * }
     */

    private void queryEthTx(String name, String type, String address) {

        String json = EtherscanApiUtils.getTransactions(address);
        JSONObject jsonObject = JSON.parseObject(json);
        if (jsonObject.getString("status").equals("1")) {
            JSONArray result = jsonObject.getJSONArray("result");
            List<JSONObject> jsonObjects = result.toJavaList(JSONObject.class);
            for (JSONObject object : jsonObjects) {
                long timeStamp = object.getLong("timeStamp");
                String hash = object.getString("hash");
                String from = object.getString("from");//转出
                String to = object.getString("to");//转入
                BigDecimal value = object.getBigDecimal("value");
                int confirmations = object.getInteger("confirmations");//确认数
                BigDecimal gasPrice = object.getBigDecimal("gasPrice");//gas的价格
                BigDecimal gasUsed = object.getBigDecimal("gasUsed");//花费的gas
                String txReceiptStatus = object.getString("txreceipt_status");//交易状态

                if (confirmations >= 6) {
                    WalletTx tx = new WalletTx();
                    tx.setWalletName(name);
                    tx.setWalletAddress(address);
                    tx.setType(type);
                    tx.setBlockTime(UnixStampUtils.unixStamp2Date(timeStamp));
                    tx.setCreateTime(new Date());
                    tx.setConfirmations(confirmations);
                    tx.setFromAddress(from);
                    tx.setToAddress(to);
                    tx.setTxHash(hash);
                    BigDecimal feeAmount = gasPrice.multiply(gasUsed);
                    tx.setFeeAmount(feeAmount);
                    tx.setInputAmount(value);
                    tx.setOutAmount(value);
                    tx.setTransUnit("wei");
                    tx.setTxReceiptStatus(txReceiptStatus);
                    tx.setState("0");

                    BigDecimal receiptAmount = null;

                    if (from.toUpperCase().equals(address.toUpperCase())) {
                        tx.setBussType("out");
                        receiptAmount = value.subtract(feeAmount).abs();

                    }
                    if (to.toUpperCase().equals(address.toUpperCase())) {
                        tx.setBussType("input");
                        receiptAmount = value;
                    }
                    tx.setReceiptAmount(receiptAmount);

                    //检查是否已存在
                    int count = walletTxMapper.selectByHashCount(hash);
                    if (count == 0) {
                        walletTxMapper.insert(tx);
                    }

                }


            }

        }


    }


    private void queryBtcBalance(String type, String address) {

        String json = BtcApiUtils.getBalance(address);
        JSONObject jsonObject = JSON.parseObject(json);
        if (jsonObject.getString("err_no").equals("0")) {
            JSONObject data = jsonObject.getJSONObject("data");
            String balance = data.getString("balance");
            Wallet wallet = new Wallet();
            wallet.setType(type);
            wallet.setAddress(address);
            BigDecimal balanceValue = new BigDecimal(balance);
            wallet.setBalance(balanceValue);
            wallet.setConvertBalance(balanceValue.divide(oneHundredMillion).setScale(8, BigDecimal.ROUND_DOWN));
            walletMapper.updateBalance(wallet);
        }


    }

    private void queryEthBalance(String type, String address) {
        String json = EtherscanApiUtils.getBalance(address);
        JSONObject jsonObject = JSON.parseObject(json);

        if (jsonObject.getString("status").equals("1")) {
            String balance = jsonObject.getString("result");
            Wallet wallet = new Wallet();
            wallet.setType(type);
            wallet.setAddress(address);
            BigDecimal balanceValue = new BigDecimal(balance);
            wallet.setBalance(balanceValue);
            wallet.setConvertBalance(balanceValue.divide(wei18).setScale(8, BigDecimal.ROUND_DOWN));
            walletMapper.updateBalance(wallet);
        }

    }


    public static void main(String[] args) {
        String a = "[\"3B2UodrHuoEhi4Xz2r5tPkbWfU62K97gBu\"],[\"3DCn5xxXf52oQWij5M7W2xRkgfxZVSmKQX\"],[\"3J9V8CNVh1RxXkBHsq9petyjvnRmcWRNPs\"],[\"3BHoG57kjGKBJhPkANez3yJEpj2381Y3et\"],[\"3AA7FsUABiLXS1m2P6juaDWVkz7HJ5BMiL\"],[\"3NSsgfApWjDYJxuzZa7pxz3jKyN1g9qxqy\"],[\"36FkP8S9DP4zgWcjFcVsYrUXfEF9QaCmsT\"],[\"3FtWGm4QiJPXSNejQLD4xYwLK9Lqh2mRFo\"],[\"3Cuxht8Sm1imB9KYPksjZ2A1qGM8AjHvrx\"],[\"3DuPeJXnVkXf48tjxopz74kzQKFQxCCeDW\"]";
        String b = "3FtWGm4QiJPXSNejQLD4xYwLK9Lqh2mRFo";

        System.out.println(a.contains(b));
    }

}
