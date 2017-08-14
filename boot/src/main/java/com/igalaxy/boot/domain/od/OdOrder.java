package com.igalaxy.boot.domain.od;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.OdProperty.OdOrderStatusEnum;
import com.igalaxy.boot.enums.OdProperty.OdPayWayEnum;

/**
 * Created by fuguolei
 */
public class OdOrder extends BaseDomain {

    private String number;
    private Double sum;
    private String name;
    private String description;
    private Long userId;
    private OdPayWayEnum payWay;

    private OdOrderStatusEnum status;

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
}
