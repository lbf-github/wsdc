package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.Uaddress;
import com.lbf.wsdc.pojo.po.UaddressExample;
import com.lbf.wsdc.pojo.po.UaddressKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UaddressMapper {
    int countByExample(UaddressExample example);

    int deleteByExample(UaddressExample example);

    int deleteByPrimaryKey(UaddressKey key);

    int insert(Uaddress record);

    int insertSelective(Uaddress record);

    List<Uaddress> selectByExample(UaddressExample example);

    Uaddress selectByPrimaryKey(UaddressKey key);

    int updateByExampleSelective(@Param("record") Uaddress record, @Param("example") UaddressExample example);

    int updateByExample(@Param("record") Uaddress record, @Param("example") UaddressExample example);

    int updateByPrimaryKeySelective(Uaddress record);

    int updateByPrimaryKey(Uaddress record);
}