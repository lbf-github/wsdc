package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.Storeinfo;
import com.lbf.wsdc.pojo.po.StoreinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreinfoMapper {
    int countByExample(StoreinfoExample example);

    int deleteByExample(StoreinfoExample example);

    int deleteByPrimaryKey(Long storeid);

    int insert(Storeinfo record);

    int insertSelective(Storeinfo record);

    List<Storeinfo> selectByExample(StoreinfoExample example);

    Storeinfo selectByPrimaryKey(Long storeid);

    int updateByExampleSelective(@Param("record") Storeinfo record, @Param("example") StoreinfoExample example);

    int updateByExample(@Param("record") Storeinfo record, @Param("example") StoreinfoExample example);

    int updateByPrimaryKeySelective(Storeinfo record);

    int updateByPrimaryKey(Storeinfo record);

    List<Storeinfo> getOpenStoreInfo();

    List<Storeinfo> getOpenStoreInfoByTypeId(Integer stypeid);

    int updateStoreInfo(Storeinfo storeinfo);

    Storeinfo getByUserId(Long userId);
}