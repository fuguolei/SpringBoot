package com.igalaxy.boot.service.usr.impl;

import com.igalaxy.boot.domain.usr.UsrAddress;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.usr.UsrAddressMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.usr.UsrAddressService;
import com.igalaxy.boot.service.usr.UsrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuguolei
 */
@Service
public class UsrAddressServiceImpl extends BaseServiceImpl<UsrAddress> implements UsrAddressService {

    @Autowired
    UsrAddressMapper usrAddressMapper;

    @Autowired
    UsrUserService usrUserService;

    @Override
    public BaseMapper<UsrAddress> getMapper() {
        return usrAddressMapper;
    }

    @Override
    public List<UsrAddress> queryMyList() {
        return usrAddressMapper.queryMyList(getUserId());
    }

    @Override
    public UsrAddress queryDefaultAddress() {
        UsrUser usrUser = usrUserService.queryById(getUserId());
        UsrAddress usrAddress = null;
        if (usrUser != null && usrUser.getDefaultAddress() != null)
            usrAddress = queryById(usrUser.getDefaultAddress());
        if (usrAddress == null)
            usrAddress = usrAddressMapper.queryMyNewOne(getUserId());
        return usrAddress;
    }
}
