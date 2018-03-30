package com.titi.controller.system;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.CustInfoFormMap;
import com.titi.entity.ProtalUserFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.ProtalUserMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * 用户登录信息 action
 * @author 陆彬峰
 *
 */
@Controller
@RequestMapping("/custprotal/")
public class ProtalUserController extends BaseController {

	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(CustController.class);

	@Inject
	private ProtalUserMapper protalUserMapper;

	@RequestMapping("listUI")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/custProtal/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		ProtalUserFormMap protalUserFormMap = getFormMap(ProtalUserFormMap.class);
		String order = " order by createdate desc";
		protalUserFormMap.put("orderby", order);
		protalUserFormMap=toFormMap(protalUserFormMap, pageNow, pageSize,null);
        pageView.setPageSize(10);
		pageView.setRecords(protalUserMapper.findProtalUserPage(protalUserFormMap));
		return pageView;
	}

	@ResponseBody
	@RequestMapping("shieldingUI")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="会员管理",methods="前台用户管理-拉黑用户")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids")[0].split(",");
		ProtalUserFormMap protalUserFormMap = getFormMap(ProtalUserFormMap.class);
		protalUserFormMap.set("status",1);
		for (String id : ids) {
			try {
				protalUserFormMap.set("userid",id);
				protalUserMapper.editEntity(protalUserFormMap);
			} catch (Exception e) {
				logger.error("拉黑用户失败", e);
				throw new SystemException("拉黑用户失败");
			}
		}
		return "success";
	}



	@ResponseBody
	@RequestMapping("disableUI")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="会员管理",methods="前台用户管理-禁用用户")//凡需要处理业务逻辑的.都需要记录操作日志
	public String updateEntity() {
		String[] ids = getParaValues("ids")[0].split(",");
		ProtalUserFormMap protalUserFormMap = getFormMap(ProtalUserFormMap.class);
		protalUserFormMap.set("status",3);
		for (String id : ids) {
			try {
				protalUserFormMap.set("userid",id);
				protalUserMapper.editEntity(protalUserFormMap);
			} catch (Exception e) {
				logger.error("禁用用户失败", e);
				throw new SystemException("禁用用户失败");
			}
		}
		return "success";
	}



	@ResponseBody
	@RequestMapping("restoreUI")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="会员管理",methods="前台用户管理-还原用户")//凡需要处理业务逻辑的.都需要记录操作日志
	public String restoreEntity() {
		String[] ids = getParaValues("ids")[0].split(",");
		ProtalUserFormMap protalUserFormMap = getFormMap(ProtalUserFormMap.class);
		protalUserFormMap.set("status",0);
		for (String id : ids) {
			try {
				protalUserFormMap.set("userid",id);
				protalUserMapper.editEntity(protalUserFormMap);
			} catch (Exception e) {
				logger.error("禁用用户失败", e);
				throw new SystemException("禁用用户失败");
			}
		}
		return "success";
	}
}