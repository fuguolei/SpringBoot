package com.igalaxy.boot.mapper.sys;

import com.igalaxy.boot.domain.sys.SysUser;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by fuguolei on 2017/7/8.
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 跟据账号查询用户
     *
     * @param account
     * @return
     */
    public SysUser getUserByAccount(String account);

    /**
     * 重置密码
     *
     * @param params
     */
    public void resetPassword(Map<String, Object> params);

    public void setPassword(SysUser sysUser);
}
