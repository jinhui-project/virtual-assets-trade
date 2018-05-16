package com.jinhui.common.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class ProductPosition {
    /**
     * 表主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 持有数
     */
    private BigDecimal positionVol;

    /**
     * 汇总日期
     */
    private Date gatherDate;

    private String accountName;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * 获取持有数
     *
     * @return position_vol - 持有数
     */
    public BigDecimal getPositionVol() {
        return positionVol;
    }

    /**
     * 设置持有数
     *
     * @param positionVol 持有数
     */
    public void setPositionVol(BigDecimal positionVol) {
        this.positionVol = positionVol;
    }

    /**
     * 获取汇总日期
     *
     * @return gather_date - 汇总日期
     */
    public Date getGatherDate() {
        return gatherDate;
    }

    /**
     * 设置汇总日期
     *
     * @param gatherDate 汇总日期
     */
    public void setGatherDate(Date gatherDate) {
        this.gatherDate = gatherDate;
    }

    /**
     * @return account_name
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }
}