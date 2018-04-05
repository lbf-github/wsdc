package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.Storetype;
import com.lbf.wsdc.pojo.po.StoretypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoretypeMapper {
    int countByExample(StoretypeExample example);

    int deleteByExample(StoretypeExample example);

    int deleteByPrimaryKey(Integer stypeid);

    int insert(Storetype record);

    int insertSelective(Storetype record);

    List<Storetype> selectByExample(StoretypeExample example);

    Storetype selectByPrimaryKey(Integer stypeid);

    int updateByExampleSelective(@Param("record") Storetype record, @Param("example") StoretypeExample example);

    int updateByExample(@Param("record") Storetype record, @Param("example") StoretypeExample example);

    int updateByPrimaryKeySelective(Storetype record);

    int updateByPrimaryKey(Storetype record);

    List<Storetype> selectTypes();
}