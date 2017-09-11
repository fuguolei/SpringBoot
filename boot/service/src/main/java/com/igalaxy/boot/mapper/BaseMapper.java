package com.igalaxy.boot.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei on 2017/7/8.
 */
public interface BaseMapper<T> {
    T queryById(long id);

    long save(T t);

    int update(T t);

    void delete(T t);

    List<T> queryPage(Map<String, Object> params);

    Integer queryPageCount(Map<String, Object> params);
}