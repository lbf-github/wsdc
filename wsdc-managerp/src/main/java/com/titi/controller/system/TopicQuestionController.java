package com.titi.controller.system;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.titi.mapper.AttachmentMapper;
import com.titi.mapper.TopicQuestionMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.AttachmentFormMap;
import com.titi.entity.TopicPicFormMap;
import com.titi.entity.TopicQuestionFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 健康社区 问题信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/topicQuestion/")
public class TopicQuestionController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(TopicQuestionController.class);
		
	@Inject
	private TopicQuestionMapper topicQuestionMapper;//注入TopicQuestionMapper
	
	@Inject
	private AttachmentMapper attachmentMapper;//注入AttachmentMapper
	
	/**
	 * 跳转到健康问答----问题页面
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/topicQuestion/list";
	}
	
	/**
	 * 分页查询健康问答----问题列表
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		TopicQuestionFormMap topicQuestionFormMap = getFormMap(TopicQuestionFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		topicQuestionFormMap.put("orderby", order);
		topicQuestionFormMap = toFormMap(topicQuestionFormMap, pageNow, pageSize,topicQuestionFormMap.getStr("orderby"));
        pageView.setRecords(topicQuestionMapper.findQuestionPage(topicQuestionFormMap));
		return pageView;
	}
	
	/**
	 * 新增健康问答--问题信息
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/topicQuestion/add";
	}
	
	/**
	 * 新增健康社区--问题信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="健康问答社区",methods="健康问答社区-新增问题")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			TopicQuestionFormMap topicQuestionFormMap = getFormMap(TopicQuestionFormMap.class);
			//设置并保存问题信息
			topicQuestionFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			topicQuestionFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			topicQuestionFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			topicQuestionFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			topicQuestionMapper.addEntity(topicQuestionFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("健康社区--新增问题失败", e);
			throw new SystemException("健康社区--新增问题失败");
		} 
		return "success";
	}

	/**
	 * 批量删除健康社区--问题信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="健康问答社区",methods="健康问答社区-删除问题")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				topicQuestionMapper.deleteByAttribute("id", id, TopicQuestionFormMap.class);
			} catch (Exception e) {
				logger.error("健康社区--删除问题失败", e);
				throw new SystemException("健康社区--删除问题失败");
			}
		}
		return "success";
	}

	/**
	 * 跳转到健康社区--问题编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("topicQuestion", topicQuestionMapper.findbyFrist("id", id, TopicQuestionFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/topicQuestion/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="健康问答社区",methods="健康问答社区-修改问题")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		TopicQuestionFormMap topicQuestionFormMap = getFormMap(TopicQuestionFormMap.class);
		try {
			topicQuestionFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			topicQuestionFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			topicQuestionMapper.editEntity(topicQuestionFormMap);
		} catch (Exception e) {
			logger.error("健康社区--修改问题失败", e);
			throw new SystemException("健康社区--修改问题失败");
		}
		return "success";
	}
	
	/**
	 * 健康问答图片修改 页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("addQuestionPicUI")
	public String addQuestionPicUI(Model model) {
		String id = getPara("id");
		String page = getPara("page");
		if(Common.isNotEmpty(id)){
			model.addAttribute("id",id);
			if("addQuestionPic".equals(page)){
				List<TopicPicFormMap> questionPicList = topicQuestionMapper.getTopicPic(id);
				model.addAttribute("questionPicList",questionPicList);
				return Common.BACKGROUND_PATH + "/system/topicQuestion/addQuestionPic";
			}else if("addAnswer".equals(page)){
				return Common.BACKGROUND_PATH + "/system/topicAnswer/list";
			}
		}
		return "success";
	}
	
	/**
	 * 健康问答图片修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateQuestionPicUI")
	@SystemLog(module="健康问答社区",methods="健康问答社区-问题图片修改")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String update(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String[] arr = request.getParameterValues("img_id");
		try {
			TopicQuestionFormMap question = getFormMap(TopicQuestionFormMap.class);
			
			//删除原有图片
			topicQuestionMapper.deleteTopicImg(String.valueOf(question.get("id")));
			
			//插入现有图片
			if(arr != null){
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					TopicPicFormMap pic = new TopicPicFormMap();
					pic.set("topic_id", question.get("id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("type", "1");
					topicQuestionMapper.saveTopicImg(pic);
				}
			}
		} catch (Exception e) {
			logger.error("修改健康问答--问题图片失败", e);
			throw new SystemException("修改健康问答--问题图片失败");
		}
		return "success";
	}
	
}