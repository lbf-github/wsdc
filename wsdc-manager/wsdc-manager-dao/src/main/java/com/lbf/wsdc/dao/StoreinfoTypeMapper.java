package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.StoreinfoType;
import com.lbf.wsdc.pojo.po.StoreinfoTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreinfoTypeMapper {
    int countByExample(StoreinfoTypeExample example);

    int deleteByExample(StoreinfoTypeExample example);

    int deleteByPrimaryKey(Long storeinfotypeid);

    int insert(StoreinfoType record);

    int insertSelective(StoreinfoType record);

    List<StoreinfoType> selectByExample(StoreinfoTypeExample example);

    StoreinfoType selectByPrimaryKey(Long storeinfotypeid);

    int updateByExampleSelective(@Param("record") StoreinfoType record, @Param("example") StoreinfoTypeExample example);

    int updateByExample(@Param("record") StoreinfoType record, @Param("example") StoreinfoTypeExample example);

    int updateByPrimaryKeySelective(StoreinfoType record);

    int updateByPrimaryKey(StoreinfoType record);
}