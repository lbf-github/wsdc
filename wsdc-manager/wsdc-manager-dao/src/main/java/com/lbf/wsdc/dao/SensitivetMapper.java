package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.Sensitivet;
import com.lbf.wsdc.pojo.po.SensitivetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SensitivetMapper {
    int countByExample(SensitivetExample example);

    int deleteByExample(SensitivetExample example);

    int deleteByPrimaryKey(Long sensitiveid);

    int insert(Sensitivet record);

    int insertSelective(Sensitivet record);

    List<Sensitivet> selectByExample(SensitivetExample example);

    Sensitivet selectByPrimaryKey(Long sensitiveid);

    int updateByExampleSelective(@Param("record") Sensitivet record, @Param("example") SensitivetExample example);

    int updateByExample(@Param("record") Sensitivet record, @Param("example") SensitivetExample example);

    int updateByPrimaryKeySelective(Sensitivet record);

    int updateByPrimaryKey(Sensitivet record);
}