package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.dao.IntroduceMapper;
import com.lbf.wsdc.pojo.po.Introduce;
import com.lbf.wsdc.service.IntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("IntroduceService")
public class IntroduceServiceiml implements IntroduceService {

	private IntroduceMapper introduceMapper;

	public IntroduceMapper getAddMapper() {
		return introduceMapper;
	}
	@Autowired
	public void setAddMapper(IntroduceMapper introduceMapper) {
		this.introduceMapper = introduceMapper;
	}
	
	@Override    
	public int Edit(Introduce introduce) {
		return introduceMapper.Edit(introduce);
	}
	
	@Override
	public Introduce GetByID(int id) {
		return introduceMapper.GetByID(id);
	}


	@Override
	public List<Introduce> Get() {
		return introduceMapper.Get();
	}

}
