package com.igalaxy.boot.enums;

import com.igalaxy.boot.enums.base.EnumValueHandler;
import com.igalaxy.boot.enums.base.ValueEnum;

/**
 * Created by fuguolei
 */
public class OdProperty {
    public enum OdPayWayEnum implements ValueEnum {
        UnKnown(0, "未知"),
        WeChat(1, "微信支付");
        private int value;
        private String desc;

        OdPayWayEnum(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public String getName() {
            return name();
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getDesc() {
            return desc;
        }

        public static OdPayWayEnum valueOf(int value) {
            switch (value) {
                case 0:
                    return UnKnown;
                case 1:
                    return WeChat;
            }
            throw new IllegalArgumentException("Cannot create evalue from value: " + value + "!");
        }
    }

    public static class OdPayWayEnumValueHandler extends EnumValueHandler<OdPayWayEnum> {
    }

    public enum OdOrderStatusEnum implements ValueEnum {
        Unpaid(1, "未支付"),
        Paid(2, "已支付"),
        Delivery(3, "已发货"),
        Finish(4, "已完成"),
        Cancel(5, "已取消"),
        TimeOut(6, "已超时");
        private int value;
        private String desc;

        OdOrderStatusEnum(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public String getName() {
            return name();
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getDesc() {
            return desc;
        }
    }

    public static class OdOrderStatusEnumValueHandler extends EnumValueHandler<OdOrderStatusEnum> {
    }
}
