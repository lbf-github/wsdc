package com.titi.mapper;

import com.titi.entity.PriceUnitTypeFormMap;
import com.titi.mapper.base.BaseMapper;

import java.util.List;

/**
 * 价格单位 接口
 * @author 陆彬峰
 *
 */
public interface PriceUnitTypeMapper extends BaseMapper{
	
	/** --提供价格单位列表信息 -- **/
	public List<PriceUnitTypeFormMap> findPriceUnitTypeList(PriceUnitTypeFormMap priceUnitTypeFormMap);
	
}
