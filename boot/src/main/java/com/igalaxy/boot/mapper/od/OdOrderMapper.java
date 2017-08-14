package com.igalaxy.boot.mapper.od;

import com.igalaxy.boot.domain.od.OdOrder;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by fuguolei on 2017/7/8.
 */
@Mapper
public interface OdOrderMapper extends BaseMapper<OdOrder> {
    void updateOdOrderByNumber(OdOrder odOrder);
}
