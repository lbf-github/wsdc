package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.Storemenu;
import com.lbf.wsdc.pojo.po.StoremenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoremenuMapper {
    int countByExample(StoremenuExample example);

    int deleteByExample(StoremenuExample example);

    int deleteByPrimaryKey(Long menuid);

    int insert(Storemenu record);

    int insertSelective(Storemenu record);

    List<Storemenu> selectByExample(StoremenuExample example);

    Storemenu selectByPrimaryKey(Long menuid);

    int updateByExampleSelective(@Param("record") Storemenu record, @Param("example") StoremenuExample example);

    int updateByExample(@Param("record") Storemenu record, @Param("example") StoremenuExample example);

    int updateByPrimaryKeySelective(Storemenu record);

    int updateByPrimaryKey(Storemenu record);

    List<Storemenu> getStoreMenuByUserId(Long userid);
}