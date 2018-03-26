package com.titi.mapper;

import com.titi.entity.LeaseEmpFormMap;
import com.titi.entity.LeaseEmpIdentityFormMap;
import com.titi.mapper.base.BaseMapper;

import java.util.List;

public interface LeaseEmpMapper extends BaseMapper{

	public List<LeaseEmpFormMap> findEmpPage(LeaseEmpFormMap map);
	
	public List<LeaseEmpIdentityFormMap> getAllIdentity();
}
