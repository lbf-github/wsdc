package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.Uorder;
import com.lbf.wsdc.pojo.po.UorderExample;
import com.lbf.wsdc.pojo.po.UorderKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UorderMapper {
    int countByExample(UorderExample example);

    int deleteByExample(UorderExample example);

    int deleteByPrimaryKey(UorderKey key);

    int insert(Uorder record);

    int insertSelective(Uorder record);

    List<Uorder> selectByExample(UorderExample example);

    Uorder selectByPrimaryKey(UorderKey key);

    int updateByExampleSelective(@Param("record") Uorder record, @Param("example") UorderExample example);

    int updateByExample(@Param("record") Uorder record, @Param("example") UorderExample example);

    int updateByPrimaryKeySelective(Uorder record);

    int updateByPrimaryKey(Uorder record);
}