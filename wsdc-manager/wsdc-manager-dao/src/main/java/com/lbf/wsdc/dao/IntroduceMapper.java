package com.lbf.wsdc.dao;

import com.lbf.wsdc.pojo.po.Introduce;

import java.util.List;


public interface IntroduceMapper {


    Introduce GetByID(Integer id);

    int Edit(Introduce record);
    
    List<Introduce> Get();
}