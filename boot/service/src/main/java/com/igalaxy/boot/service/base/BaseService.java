package com.igalaxy.boot.service.base;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.PageData;

import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei on 2017/7/8.
 */
public interface BaseService<T> {
    T queryById(long id);

    BaseResult<T> save(T t);

    BaseResult update(T t);

    BaseResult delete(T t);

    PageData queryPage(Map<String, Object> params);

    List<T> queryAllData(Map<String, Object> params);
}
