package com.igalaxy.boot.service.auth.impl;


import com.google.gson.Gson;
import com.igalaxy.boot.domain.auth.SysStgData;
import com.igalaxy.boot.enums.SysProperty.StgFilePrefixEnum;
import com.igalaxy.boot.enums.SysProperty.StgFileStatusEnum;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.auth.SysStgDataMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.auth.SysStgDataService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Map;
import java.util.UUID;

/**
 * Created by fuguolei
 */
@Service
public class SysStgDataServiceImpl extends BaseServiceImpl<SysStgData> implements SysStgDataService {

    @Autowired
    SysStgDataMapper sysStgDataMapper;

    private Gson gson = new Gson();

    public SysStgData upload(CommonsMultipartFile file, StgFilePrefixEnum prefix, Long bizId) {
        return upload(file, prefix, bizId, StgFileStatusEnum.Normal);
    }

    public SysStgData upload(CommonsMultipartFile file, StgFilePrefixEnum prefix, Long bizId, StgFileStatusEnum status) {
        try {
            String uploadName = file.getOriginalFilename();

            String fileName = prefix.getNamespaces() + "/" + UUID.randomUUID().toString().replace("-", "") + uploadName.substring(uploadName.lastIndexOf("."));
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            System.out.println(res.bodyString());

            QiNiuReponse reponse = gson.fromJson(res.bodyString(), QiNiuReponse.class);

            String protocol = "http";
            String url = String.format("%s://%s/%s", protocol, DOMAIN, reponse.getKey());
            SysStgData sysStgData = new SysStgData();
            sysStgData.setBizId(bizId != null ? String.valueOf(bizId) : null);
            sysStgData.setHash(reponse.getHash());
            sysStgData.setKey(reponse.getKey());
            sysStgData.setDomain(DOMAIN);
            sysStgData.setUrl(url);
            sysStgData.setStatus(status);
            sysStgData.setType(file.getContentType());
            sysStgData.setPrefix(prefix);
            sysStgData.setUploadName(uploadName);
            sysStgData.setBucket(BUCKETNAME);
            save(sysStgData);
            return sysStgData;

        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;
    }

    @Override
    public void updateBizIdStatusById(long id, Long bizId, StgFileStatusEnum status) {
        Map<String, Object> params = getParamsMap();
        params.put("id", id);
        if (bizId != null)
            params.put("bizId", bizId);
        if (status != null)
            params.put("status", status);
        sysStgDataMapper.updateBizIdStatusById(params);
    }

    static String ACCESS_KEY = "ucHlHGcvWskvOQmwZGuuud2rLTgi6h1Wp3PtUAJM";
    static String SECRET_KEY = "DjmR5JhQJQHR6Yo0o_GibnY-Gx7JAktljHB95cj5";
    static String BUCKETNAME = "image";
    String DOMAIN = "image.gogocbd.com";
    static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    static UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone2()));

    public static String getUpToken() {
        return auth.uploadToken(BUCKETNAME);
    }

    @Override
    public BaseMapper<SysStgData> getMapper() {
        return sysStgDataMapper;
    }

    static class QiNiuReponse {
        private String hash;
        private String key;

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
    }
}
