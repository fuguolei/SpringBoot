package com.igalaxy.boot.enums;

import com.igalaxy.boot.enums.base.EnumValueHandler;
import com.igalaxy.boot.enums.base.ValueEnum;

/**
 * Created by fuguolei
 */
public class SysProperty {
    public static enum WhetherEnum implements ValueEnum {
        Yes(1, "是"),
        No(0, "否");
        private int value;
        private String desc;

        WhetherEnum(int value, String desc) {
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

    public static class WhetherEnumValueHandler extends EnumValueHandler<WhetherEnum> {
    }

    public static enum LogStatusEnum implements ValueEnum {
        Success(1, "成功"),
        Fail(0, "失败");
        private int value;
        private String desc;

        LogStatusEnum(int value, String desc) {
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

        public LogStatusEnum valueOf(int value) {
            switch (value) {
                case 1:
                    return Success;
                case 0:
                    return Fail;
            }
            throw new IllegalArgumentException("Cannot create evalue from value: " + value + "!");
        }
    }

    public static class LogStatusEnumValueHandler extends EnumValueHandler<LogStatusEnum> {
    }


    public static enum StgFilePrefixEnum implements ValueEnum {

        UserPhoto(1, "用户照片", "user/photo"),
        CaseImage(2, "案例图片", "case/image");
        private int value;
        private String desc;
        private String namespaces;

        StgFilePrefixEnum(int value, String desc, String namespaces) {
            this.value = value;
            this.namespaces = namespaces;
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

        public String getDesc() {
            return desc;
        }

        public String getNamespaces() {
            return namespaces;
        }

        public static StgFilePrefixEnum valueOf(int value) {
            switch (value) {
                case 1:
                    return UserPhoto;
                case 2:
                    return CaseImage;
            }
            throw new IllegalArgumentException("Cannot create evalue from value: " + value + "!");
        }
    }

    public static class StgFilePrefixEnumValueHandler extends EnumValueHandler<StgFilePrefixEnum> {
    }


    /**
     * 3方客户端绑定关系
     */
    public static enum StgFileStatusEnum implements ValueEnum {
        Normal(1, "正常"),
        Temp(2, "临时存储");
        private int value;
        private String desc;

        StgFileStatusEnum(int value, String desc) {
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

        public String getDesc() {
            return desc;
        }

        public static StgFileStatusEnum valueOf(int value) {
            switch (value) {
                case 1:
                    return Normal;
                case 2:
                    return Temp;
            }
            throw new IllegalArgumentException("Cannot create evalue from value: " + value + "!");
        }
    }

    public static class StgFileStatusEnumValueHandler extends EnumValueHandler<StgFileStatusEnum> {
    }

    public enum SysSaleEnum implements ValueEnum {
        NoSale(0, "下架"),
        Sale(1, "上架");
        private int value;
        private String desc;

        SysSaleEnum(int value, String desc) {
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

        public SysSaleEnum valueOf(int value) {
            switch (value) {
                case 0:
                    return NoSale;
                case 1:
                    return Sale;
            }
            throw new IllegalArgumentException("Cannot create evalue from value: " + value + "!");
        }
    }

    public static class SysSaleEnumValueHandler extends EnumValueHandler<SysSaleEnum> {
    }
}
