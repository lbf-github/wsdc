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
import com.titi.mapper.MsgMapper;
import com.titi.mapper.MsgRecordMapper;
import com.titi.mapper.TopicAnswerMapper;
import com.titi.mapper.TopicQuestionMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.AnswerLikeFormMap;
import com.titi.entity.AttachmentFormMap;
import com.titi.entity.MsgFormMap;
import com.titi.entity.MsgRecordFormMap;
import com.titi.entity.TopicAnswerFormMap;
import com.titi.entity.TopicPicFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 健康社区 --答案信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/topicAnswer/")
public class TopicAnswerController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(TopicAnswerController.class);
		
	@Inject
	private TopicAnswerMapper topicAnswerMapper;//注入topicAnswerMapper
	
	@Inject
	private TopicQuestionMapper topicQuestionMapper;//注入topicQuestionMapper
	
	@Inject
	private MsgMapper msgMapper;//注入msgMapper
	
	@Inject
	private AttachmentMapper attachmentMapper;//注入AttachmentMapper
	
	@Inject
	private MsgRecordMapper msgRecordMapper;//注入MsgRecordMapper
	
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
		String topic_id = getPara("topic_id");
		TopicAnswerFormMap topicAnswerFormMap = getFormMap(TopicAnswerFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		topicAnswerFormMap.put("orderby", order);
		topicAnswerFormMap.put("topic_id",topic_id);
		topicAnswerFormMap = toFormMap(topicAnswerFormMap, pageNow, pageSize,topicAnswerFormMap.getStr("orderby"));
        pageView.setRecords(topicAnswerMapper.findByPage(topicAnswerFormMap));
		return pageView;
	}
	
	/**
	 * 新增健康问答--答案信息
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		String topic_id = getPara("topic_id");
		if(Common.isNotEmpty(topic_id)){
			model.addAttribute("topic_id",topic_id);
		}
		return Common.BACKGROUND_PATH + "/system/topicAnswer/add";
	}
	
	/**
	 * 新增健康社区--答案信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="健康问答社区",methods="健康问答社区-新增答案")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			TopicAnswerFormMap topicAnswerFormMap = getFormMap(TopicAnswerFormMap.class);
			//设置并保存问题信息
			topicAnswerFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			topicAnswerFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			topicAnswerFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			topicAnswerFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			topicAnswerMapper.addEntity(topicAnswerFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("健康社区--新增答案失败", e);
			throw new SystemException("健康社区--新增答案失败");
		} 
		return "success";
	}
	
	/**
	 * 健康问答答案图片 页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("addAnswerPicUI")
	public String addAnswerPicUI(Model model) {
		String id = getPara("id");
		String page = getPara("page");
		if(Common.isNotEmpty(id)){
			model.addAttribute("id",id);
			if("addAnswerPic".equals(page)){
				List<TopicPicFormMap> answerPicList = topicQuestionMapper.getTopicPic(id);
				model.addAttribute("answerPicList",answerPicList);
				return Common.BACKGROUND_PATH + "/system/topicAnswer/addAnswerPic";
			}
		}
		return "success";
	}
	
	/**
	 * 健康问答图片修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateAnswerPicUI")
	@SystemLog(module="健康问答社区",methods="健康问答社区-答案图片修改")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String updateAnswerPic(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String[] arr = request.getParameterValues("img_id");
		try {
			TopicAnswerFormMap answer = getFormMap(TopicAnswerFormMap.class);
			
			//删除原有图片
			topicQuestionMapper.deleteTopicImg(String.valueOf(answer.get("id")));
			
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
					pic.set("topic_id", answer.get("id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("type", "2");
					topicQuestionMapper.saveTopicImg(pic);
				}
			}
		} catch (Exception e) {
			logger.error("修改健康问答--答案图片失败", e);
			throw new SystemException("修改健康问答--答案图片失败");
		}
		return "success";
	}

	/**
	 * 批量删除健康社区--答案信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="健康问答社区",methods="健康问答社区-删除答案")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				topicAnswerMapper.deleteByAttribute("id", id, TopicAnswerFormMap.class);
			} catch (Exception e) {
				logger.error("健康社区--删除答案失败", e);
				throw new SystemException("健康社区--删除答案失败");
			}
		}
		return "success";
	}

	/**
	 * 跳转到健康社区--答案编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("topicAnswer", topicAnswerMapper.findbyFrist("id", id, TopicAnswerFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/topicAnswer/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="健康问答社区",methods="健康问答社区-修改答案")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		TopicAnswerFormMap topicAnswerFormMap = getFormMap(TopicAnswerFormMap.class);
		try {
			topicAnswerFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			topicAnswerFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			topicAnswerMapper.editEntity(topicAnswerFormMap);
			
			if("1".equals(topicAnswerFormMap.get("best_yn"))){
				MsgRecordFormMap msgRecordFormMap = getFormMap(MsgRecordFormMap.class);
				msgRecordFormMap.set("title", "消息通知");
				msgRecordFormMap.set("content", "【健康问答】您的回答很赞，已被提问者设置为最佳答案!");
				msgRecordFormMap.set("cust_id", topicAnswerFormMap.get("create_by"));
				msgRecordFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
				msgRecordMapper.addEntity(msgRecordFormMap);
				
				MsgFormMap msg = getFormMap(MsgFormMap.class);
				msg.set("fr_id", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
				msg.set("create_date", new Timestamp(System.currentTimeMillis()));
				msg.set("to_id", topicAnswerFormMap.get("create_by"));
				msg.set("record_id", msgRecordFormMap.get("id"));
				msg.set("title", "消息通知");
				msg.set("content", "【健康问答】您的回答很赞，已被提问者设置为最佳答案!");
				msgMapper.addEntity(msg);
			}
		} catch (Exception e) {
			logger.error("健康社区--修改答案失败", e);
			throw new SystemException("健康社区--修改答案失败");
		}
		return "success";
	}
	
	/**
	 * 答案点赞人列表
	 * @param model
	 * @return
	 */
	@RequestMapping("toLike")
	public String toLike(Model model) {
		String id = getPara("id");
		model.addAttribute("id", id);
		return Common.BACKGROUND_PATH + "/system/topicAnswer/like";
	}
	
	@ResponseBody
	@RequestMapping("findLikeByPage")
	public PageView findLikeByPage(String pageNow,String pageSize,String column,String sort) {
		String id = getPara("answer_id");
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}
		AnswerLikeFormMap map = new AnswerLikeFormMap();
		map.put("orderby", order);
		map = toFormMap(map, pageNow, pageSize,map.getStr("orderby"));
		map.put("rel_id",id);
		List<AnswerLikeFormMap> list = topicQuestionMapper.selectAnswerLikeList(map);
//		List<AnswerLikeFormMap> list = topicQuestionMapper.findByPage(map);
        pageView.setRecords(list);
		return pageView;
	}
	
}