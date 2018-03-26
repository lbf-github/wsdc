package com.titi.mapper;

import java.util.List;

import com.titi.entity.CustAccountFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 前台用户账户信息 Dao层接口
 * @author 曾雄
 *
 */
public interface CustAccountMapper extends BaseMapper{

	//分页查询账户信息
	public List<CustAccountFormMap> findCustAccountPage(CustAccountFormMap custAccountFormMap);
	
	//查询账户信息列表(用于附属信息管理中显示下拉信息)
	public List<CustAccountFormMap> findCustAccountList(CustAccountFormMap custAccountFormMap);
	
	//新增账户信息数据
	public void addCustAccountEntity(CustAccountFormMap custAccountFormMap);
	
}
