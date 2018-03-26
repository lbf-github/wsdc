package com.titi.mapper;

import java.util.List;

import com.titi.entity.SeoCasesFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 经典案例信息 Dao层接口
 * @author 曾雄
 *
 */
public interface SeoCasesMapper extends BaseMapper{

	/** --分页查询经典案例信息 -- **/
	public List<SeoCasesFormMap> findSeoCasesPage(SeoCasesFormMap seoCasesFormMap);
	
}
