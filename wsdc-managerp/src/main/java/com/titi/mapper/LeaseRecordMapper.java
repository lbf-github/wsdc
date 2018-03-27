package com.titi.mapper;

import com.titi.entity.LeaseRecordFormMap;
import com.titi.mapper.base.BaseMapper;

import java.util.List;

/**
 * 租借记录信息接口
 * @author 陆彬峰
 *
 */
public interface LeaseRecordMapper extends BaseMapper{

	/** -- 租借记录列表信息 -- **/
	public List<LeaseRecordFormMap> findLeaseRecordPage(LeaseRecordFormMap leaseRecordFormMap);
	
}
