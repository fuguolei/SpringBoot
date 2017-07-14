package com.igalaxy.boot.domain.sys;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.SysProperty.StgFilePrefixEnum;
import com.igalaxy.boot.enums.SysProperty.StgFileStatusEnum;

/**
 * Created by fuguolei
 */
public class SysStgData extends BaseDomain {
    private String hash;
    private String key;
    private String domain;
    private String url;
    private StgFilePrefixEnum prefix;
    private StgFileStatusEnum status;
    private String bizId;
    private String bucket;
    private String type;
    private String uploadName;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public StgFileStatusEnum getStatus() {
        return status;
    }

    public void setStatus(StgFileStatusEnum status) {
        this.status = status;
    }

    public StgFilePrefixEnum getPrefix() {
        return prefix;
    }

    public void setPrefix(StgFilePrefixEnum prefix) {
        this.prefix = prefix;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getType() {
        return type;
    }
}
