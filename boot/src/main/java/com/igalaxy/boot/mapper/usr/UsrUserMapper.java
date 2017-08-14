package com.igalaxy.boot.mapper.usr;

import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by fuguolei on 2017/7/8.
 */
@Mapper
public interface UsrUserMapper extends BaseMapper<UsrUser> {
    /**
     * 跟据账号查询用户
     *
     * @param account
     * @return
     */
    public UsrUser getUserByAccount(String account);

    /**
     * 重置密码
     *
     * @param params
     */
    public void resetPassword(Map<String, Object> params);

    public void setPassword(UsrUser usrUser);

    UsrUser queryByOpenId(String openId);
}
