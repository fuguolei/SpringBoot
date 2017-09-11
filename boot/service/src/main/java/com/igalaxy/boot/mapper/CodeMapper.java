package com.igalaxy.boot.mapper;

import com.igalaxy.boot.domain.CodeDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fuguolei on 2017/7/8.
 */
@Mapper
public interface CodeMapper {
    List<CodeDomain> queryByName(String name);
}
