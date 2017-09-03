package com.igalaxy.boot.enums;

import com.igalaxy.boot.enums.base.EnumValueHandler;
import com.igalaxy.boot.enums.base.ValueEnum;

/**
 * Created by fuguolei
 */
public class GdProperty {
    public enum GdSaleEnum implements ValueEnum {
        NoSale(0, "下架"),
        Sale(1, "上架");
        private int value;
        private String desc;

        GdSaleEnum(int value, String desc) {
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

        public GdSaleEnum valueOf(int value) {
            switch (value) {
                case 0:
                    return NoSale;
                case 1:
                    return Sale;
            }
            throw new IllegalArgumentException("Cannot create evalue from value: " + value + "!");
        }
    }

    public static class GdSaleEnumValueHandler extends EnumValueHandler<GdSaleEnum> {
    }
}
