package com.igalaxy.boot.domain.od;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.OdProperty.OdOrderStatusEnum;
import com.igalaxy.boot.enums.OdProperty.OdPayWayEnum;

import java.util.List;

import static com.igalaxy.boot.enums.OdProperty.OdPayWayEnum.UnKnown;

/**
 * Created by fuguolei
 */
public class OdOrder extends BaseDomain {

    private String number;
    private Double sum;
    private String name;
    private String description;
    private Long userId;
    private OdPayWayEnum payWay = UnKnown;
    private OdOrderStatusEnum status;
    private Long expressId;
    private String expressNumber;


    //链表查询
    private List<OdOrderSKU> skus;
    private String expressName;
    private String userName;

    public List<OdOrderSKU> getSkus() {
        return skus;
    }

    public void setSkus(List<OdOrderSKU> skus) {
        this.skus = skus;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OdPayWayEnum getPayWay() {
        return payWay;
    }

    public void setPayWay(OdPayWayEnum payWay) {
        this.payWay = payWay;
    }

    public OdOrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OdOrderStatusEnum status) {
        this.status = status;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
