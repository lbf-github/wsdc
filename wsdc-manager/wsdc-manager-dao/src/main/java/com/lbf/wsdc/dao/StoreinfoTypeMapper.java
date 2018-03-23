package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.StoreinfoTypeExample;
import com.lbf.wsdc.pojo.po.StoreinfoTypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreinfoTypeMapper {
    int countByExample(StoreinfoTypeExample example);

    int deleteByExample(StoreinfoTypeExample example);

    int deleteByPrimaryKey(StoreinfoTypeKey key);

    int insert(StoreinfoTypeKey record);

    int insertSelective(StoreinfoTypeKey record);

    List<StoreinfoTypeKey> selectByExample(StoreinfoTypeExample example);

    int updateByExampleSelective(@Param("record") StoreinfoTypeKey record, @Param("example") StoreinfoTypeExample example);

    int updateByExample(@Param("record") StoreinfoTypeKey record, @Param("example") StoreinfoTypeExample example);
}