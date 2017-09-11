package com.igalaxy.boot.mapper.od;

import com.igalaxy.boot.domain.od.OdOrderSKU;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fuguolei on 2017/7/8.
 */
@Mapper
public interface OdOrderSKUMapper extends BaseMapper<OdOrderSKU> {

    void batchSave(List<OdOrderSKU> list);

    float querySumByOrderId(Long orderId);

    List<OdOrderSKU> queryListByOrderId(Long orderId);
}
