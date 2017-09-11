package com.igalaxy.boot.service.base;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.PageData;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Long getUserId() {
        Long userId = SessionUtils.getUserId();
        return userId != null ? userId : 0l;
    }

    protected Map<String, Object> getParamsMap() {
        Map<String, Object> params = new HashMap();
        Long userId = getUserId();
        params.put("createUser", userId);
        params.put("updateUser", userId);
        return params;
    }

    public abstract BaseMapper<T> getMapper();

    @Override
    public T queryById(long id) {
        return getMapper().queryById(id);
    }

    @Override
    public BaseResult<T> save(T t) {
        Long userId = getUserId();
        if (t != null && t instanceof BaseDomain) {
            ((BaseDomain) t).setCreateUser(userId);
            ((BaseDomain) t).setUpdateUser(userId);
        }
        getMapper().save(t);
        return BaseResult.ok(0, "添加成功", t);
    }

    @Override
    public BaseResult update(T t) {
        Long userId = getUserId();
        if (t != null && t instanceof BaseDomain) {
            ((BaseDomain) t).setUpdateUser(userId);
        }
        getMapper().update(t);
        return BaseResult.ok("更新成功");
    }

    @Override
    public BaseResult delete(T t) {
        Long userId = getUserId();
        if (t != null && t instanceof BaseDomain) {
            ((BaseDomain) t).setUpdateUser(userId);
        }
        getMapper().delete(t);
        return BaseResult.ok("删除成功");
    }

    @Override
    public PageData queryPageList(Map<String, Object> params) {
        PageData page = new PageData();
        page.setRows(getMapper().queryPage(params));
        page.setTotal(getMapper().queryPageCount(params));
        return page;
    }

    @Override
    public List<T> queryAllPageData(Map<String, Object> params) {
        List<T> list = getMapper().queryPage(params);
        return list;
    }
}
