package com.igalaxy.boot.service.base;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.ListViewPage;

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

    ListViewPage queryPageList(Map<String, Object> params);

    List<T> queryAllPageData(Map<String, Object> params);
}
