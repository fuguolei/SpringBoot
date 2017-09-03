package com.igalaxy.boot.mapper.usr;

import com.igalaxy.boot.domain.usr.UsrAddress;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fuguolei on 2017/7/8.
 */
@Mapper
public interface UsrAddressMapper extends BaseMapper<UsrAddress> {

    List<UsrAddress> queryMyList(long userId);

    UsrAddress queryMyNewOne(long userId);
}
