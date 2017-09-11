package com.igalaxy.boot.service.auth;


import com.igalaxy.boot.domain.auth.SysStgData;
import com.igalaxy.boot.enums.SysProperty.StgFilePrefixEnum;
import com.igalaxy.boot.enums.SysProperty.StgFileStatusEnum;
import com.igalaxy.boot.service.base.BaseService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by fuguolei
 */
public interface SysStgDataService extends BaseService<SysStgData> {

    SysStgData upload(CommonsMultipartFile file, StgFilePrefixEnum fileType, Long bizId);

    SysStgData upload(CommonsMultipartFile file, StgFilePrefixEnum fileType, Long bizId, StgFileStatusEnum status);

    void updateBizIdStatusById(long id, Long bizId, StgFileStatusEnum status);
}