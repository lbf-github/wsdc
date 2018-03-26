package com.titi.controller.system;

import java.util.Arrays;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.mapper.ClassifyItemMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.ClassifyItemFormMap;
import com.titi.exception.SystemException;

/**
 * 商品分类关联信息  action
 * @author 曾雄
 */
@Controller
@RequestMapping("/classifyItem/")
public class ClassifyItemController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(ClassifyItemController.class);
		
	@Inject
	private ClassifyItemMapper classifyItemMapper;//注入ClassifyItemMapper
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="商品分类关联管理",methods="商品分类关联管理-新增商品分类关联信息")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		String[] idStr = Arrays.asList(getParaValues("ids")).get(0).split(",");
		String classify_id = getPara("classify_id");
		for(String id : idStr){
			ClassifyItemFormMap classifyItemFormMap = getFormMap(ClassifyItemFormMap.class);
			classifyItemFormMap.set("classify_id", classify_id);
			classifyItemFormMap.set("item_id", id);
			int isExist = classifyItemMapper.findIsExist(classifyItemFormMap);
			try {
				if(isExist == 0){
					classifyItemFormMap.put("classify_id", classify_id);
					classifyItemFormMap.put("item_id", id);
					classifyItemMapper.addEntity(classifyItemFormMap);//新增后返回新增信息
				}
			} catch (Exception e) {
				logger.error("新增商品分类管理系信息失败", e);
				throw new SystemException("新增商品分类管理系信息失败");
			} 
		}
		return "success";
	}
	
}