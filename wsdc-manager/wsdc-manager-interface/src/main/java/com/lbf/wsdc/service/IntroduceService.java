package com.lbf.wsdc.service;


import com.lbf.wsdc.pojo.po.Introduce;

import java.util.List;

public interface IntroduceService {

	public int Edit(Introduce j);
	public Introduce GetByID(int id);
	public List<Introduce> Get();

}
