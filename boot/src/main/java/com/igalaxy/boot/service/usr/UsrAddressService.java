package com.igalaxy.boot.service.usr;

import com.igalaxy.boot.domain.usr.UsrAddress;
import com.igalaxy.boot.service.base.BaseService;

import java.util.List;

/**
 * Created by fuguolei
 */
public interface UsrAddressService extends BaseService<UsrAddress> {

    List<UsrAddress> queryMyList();

    UsrAddress queryDefaultAddress();
}
