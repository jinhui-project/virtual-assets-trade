package com.jinhui.common.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class WalletTx {
    /**
     * 表主键
     */
    private Long id;

    /**
     * 钱包名称
     */
    private String walletName;

    /**
     * 钱包地址
     */
    private String walletAddress;

    /**
     * 钱包类型
     */
    private String type;

    /**
     * 交易发起方
     */
    private String fromAddress;

    /**
     * 交易接收方
     */
    private String toAddress;

    /**
     * 交易hash
     */
    private String txHash;

    /**
     * 出块时间
     */
    private Date blockTime;

    /**
     * 输入金额
     */
    private BigDecimal inputAmount;

    /**
     * 输出金额
     */
    private BigDecimal outAmount;

    /**
     * 手续费用
     */
    private BigDecimal feeAmount;

    /**
     * 实际交易金额
     */
    private BigDecimal receiptAmount;

    /**
     * 金额单位
     */
    private String transUnit;

    /**
     * 确认数
     */
    private Integer confirmations;

    private String txReceiptStatus;

    /**
     * 交易类型:转入-input，转出-out
     */
    private String bussType;

    /**
     * 0-有效，1-无效（只能核对一次，核对后变为无效记录）
     */
    private String state;

    /**
     * 记录时间
     */
    private Date createTime;

    /**
     * 获取表主键
     *
     * @return id - 表主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置表主键
     *
     * @param id 表主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取钱包名称
     *
     * @return wallet_name - 钱包名称
     */
    public String getWalletName() {
        return walletName;
    }

    /**
     * 设置钱包名称
     *
     * @param walletName 钱包名称
     */
    public void setWalletName(String walletName) {
        this.walletName = walletName == null ? null : walletName.trim();
    }

    /**
     * 获取钱包地址
     *
     * @return wallet_address - 钱包地址
     */
    public String getWalletAddress() {
        return walletAddress;
    }

    /**
     * 设置钱包地址
     *
     * @param walletAddress 钱包地址
     */
    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress == null ? null : walletAddress.trim();
    }

    /**
     * 获取钱包类型
     *
     * @return type - 钱包类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置钱包类型
     *
     * @param type 钱包类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取交易发起方
     *
     * @return from_address - 交易发起方
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * 设置交易发起方
     *
     * @param fromAddress 交易发起方
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress == null ? null : fromAddress.trim();
    }

    /**
     * 获取交易接收方
     *
     * @return to_address - 交易接收方
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * 设置交易接收方
     *
     * @param toAddress 交易接收方
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress == null ? null : toAddress.trim();
    }

    /**
     * 获取交易hash
     *
     * @return tx_hash - 交易hash
     */
    public String getTxHash() {
        return txHash;
    }

    /**
     * 设置交易hash
     *
     * @param txHash 交易hash
     */
    public void setTxHash(String txHash) {
        this.txHash = txHash == null ? null : txHash.trim();
    }

    /**
     * 获取出块时间
     *
     * @return block_time - 出块时间
     */
    public Date getBlockTime() {
        return blockTime;
    }

    /**
     * 设置出块时间
     *
     * @param blockTime 出块时间
     */
    public void setBlockTime(Date blockTime) {
        this.blockTime = blockTime;
    }

    /**
     * 获取输入金额
     *
     * @return input_amount - 输入金额
     */
    public BigDecimal getInputAmount() {
        return inputAmount;
    }

    /**
     * 设置输入金额
     *
     * @param inputAmount 输入金额
     */
    public void setInputAmount(BigDecimal inputAmount) {
        this.inputAmount = inputAmount;
    }

    /**
     * 获取输出金额
     *
     * @return out_amount - 输出金额
     */
    public BigDecimal getOutAmount() {
        return outAmount;
    }

    /**
     * 设置输出金额
     *
     * @param outAmount 输出金额
     */
    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    /**
     * 获取手续费用
     *
     * @return fee_amount - 手续费用
     */
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    /**
     * 设置手续费用
     *
     * @param feeAmount 手续费用
     */
    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * 获取实际交易金额
     *
     * @return receipt_amount - 实际交易金额
     */
    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    /**
     * 设置实际交易金额
     *
     * @param receiptAmount 实际交易金额
     */
    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    /**
     * 获取金额单位
     *
     * @return trans_unit - 金额单位
     */
    public String getTransUnit() {
        return transUnit;
    }

    /**
     * 设置金额单位
     *
     * @param transUnit 金额单位
     */
    public void setTransUnit(String transUnit) {
        this.transUnit = transUnit == null ? null : transUnit.trim();
    }

    /**
     * 获取确认数
     *
     * @return confirmations - 确认数
     */
    public Integer getConfirmations() {
        return confirmations;
    }

    /**
     * 设置确认数
     *
     * @param confirmations 确认数
     */
    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    /**
     * @return tx_receipt_status
     */
    public String getTxReceiptStatus() {
        return txReceiptStatus;
    }

    /**
     * @param txReceiptStatus
     */
    public void setTxReceiptStatus(String txReceiptStatus) {
        this.txReceiptStatus = txReceiptStatus == null ? null : txReceiptStatus.trim();
    }

    /**
     * 获取交易类型:转入-input，转出-out
     *
     * @return buss_type - 交易类型:转入-input，转出-out
     */
    public String getBussType() {
        return bussType;
    }

    /**
     * 设置交易类型:转入-input，转出-out
     *
     * @param bussType 交易类型:转入-input，转出-out
     */
    public void setBussType(String bussType) {
        this.bussType = bussType == null ? null : bussType.trim();
    }

    /**
     * 获取0-有效，1-无效（只能核对一次，核对后变为无效记录）
     *
     * @return state - 0-有效，1-无效（只能核对一次，核对后变为无效记录）
     */
    public String getState() {
        return state;
    }

    /**
     * 设置0-有效，1-无效（只能核对一次，核对后变为无效记录）
     *
     * @param state 0-有效，1-无效（只能核对一次，核对后变为无效记录）
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 获取记录时间
     *
     * @return create_time - 记录时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录时间
     *
     * @param createTime 记录时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}