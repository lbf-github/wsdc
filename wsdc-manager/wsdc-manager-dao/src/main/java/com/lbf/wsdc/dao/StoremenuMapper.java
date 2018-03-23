package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.Storemenu;
import com.lbf.wsdc.pojo.po.StoremenuExample;
import com.lbf.wsdc.pojo.po.StoremenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoremenuMapper {
    int countByExample(StoremenuExample example);

    int deleteByExample(StoremenuExample example);

    int deleteByPrimaryKey(StoremenuKey key);

    int insert(Storemenu record);

    int insertSelective(Storemenu record);

    List<Storemenu> selectByExample(StoremenuExample example);

    Storemenu selectByPrimaryKey(StoremenuKey key);

    int updateByExampleSelective(@Param("record") Storemenu record, @Param("example") StoremenuExample example);

    int updateByExample(@Param("record") Storemenu record, @Param("example") StoremenuExample example);

    int updateByPrimaryKeySelective(Storemenu record);

    int updateByPrimaryKey(Storemenu record);
}