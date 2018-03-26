package com.titi.mapper;

import java.util.List;

import com.titi.entity.ItemSaleRecordFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 商品销售记录信息 Dao层接口
 * @author 曾雄
 *
 */
public interface ItemSaleRecordMapper extends BaseMapper{

	/** --分页查询商品的销售记录信息 -- **/
	public List<ItemSaleRecordFormMap> findItemSaleRecordPage(ItemSaleRecordFormMap itemSaleRecordFormMap);
	
}
