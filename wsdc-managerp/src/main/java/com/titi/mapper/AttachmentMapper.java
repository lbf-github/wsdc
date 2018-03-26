package com.titi.mapper;

import com.titi.entity.AttachmentFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 附件管理 Dao层接口
 * @author 曾雄
 *
 */
public interface AttachmentMapper extends BaseMapper{
	
	/** --附件新增数据 -- **/
	public boolean addAttachmentEntity(AttachmentFormMap attachmentFormMap);
	
	/** --根据id获取附件信息 -- **/
	public AttachmentFormMap selectAttaById(String id);
	
}
