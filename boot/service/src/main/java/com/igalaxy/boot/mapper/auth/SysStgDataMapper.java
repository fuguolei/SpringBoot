package com.igalaxy.boot.mapper.auth;

import com.igalaxy.boot.domain.auth.SysStgData;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Mapper
public interface SysStgDataMapper extends BaseMapper<SysStgData> {
    void updateBizIdStatusById(Map<String, Object> params);
}