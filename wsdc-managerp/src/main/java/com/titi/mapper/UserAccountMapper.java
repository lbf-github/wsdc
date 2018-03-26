package com.titi.mapper;

import com.titi.entity.UserAccountFormMap;
import com.titi.mapper.base.BaseMapper;

import java.util.List;

/**
 * 账户信息 接口
 * @author 曾雄
 *
 */
public interface UserAccountMapper extends BaseMapper{
	
	/** --查询前台用户列表信息 -- **/
	public List<UserAccountFormMap> findUserAccountPage(UserAccountFormMap userAccountFormMap);
	
	public List<UserAccountFormMap> getAllUser();
}
