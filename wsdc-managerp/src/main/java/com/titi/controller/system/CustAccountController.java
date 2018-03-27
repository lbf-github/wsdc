package com.titi.controller.system;

import java.util.List;

import javax.inject.Inject;

import com.titi.entity.CustInfoFormMap;
import com.titi.mapper.CustInfoMapper;
import com.titi.util.ShareCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.mapper.CustAccountMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.CustAccountFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import com.titi.util.PwdUtil;

/**
 * 用户账户信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/custAccount/")
public class CustAccountController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(CustAccountController.class);
		
	@Inject
	private CustAccountMapper custAccountMapper;//注入CustAccountMapper

	@Inject
	private CustInfoMapper custInfoMapper;

	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/custAccount/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		CustAccountFormMap custAccountFormMap = getFormMap(CustAccountFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		custAccountFormMap.put("orderby", order);
		custAccountFormMap = toFormMap(custAccountFormMap, pageNow, pageSize,custAccountFormMap.getStr("orderby"));
        pageView.setRecords(custAccountMapper.findCustAccountPage(custAccountFormMap));
		return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/custAccount/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="前台账户管理",methods="前台账户管理-新增账户")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			CustAccountFormMap custAccountFormMap = getFormMap(CustAccountFormMap.class);
			String encodePwd = PwdUtil.encryptPassword((String) custAccountFormMap.get("password"));
			custAccountFormMap.set("password", encodePwd);
			custAccountMapper.addEntity(custAccountFormMap);//新增后返回新增信息

			CustInfoFormMap custInfoFormMap = getFormMap(CustInfoFormMap.class);
			custInfoFormMap.set("account_id",custAccountFormMap.get("id") );
			custInfoFormMap.set("shareCode", ShareCodeUtil.toSerialCode(Long.parseLong((String)custAccountFormMap.get("mobile")) ) );
			custInfoMapper.addEntity(custInfoFormMap);

		} catch (Exception e) {
			logger.error("新增账户失败", e);
			throw new SystemException("新增账户失败");
		} 
		return "success";
	}

	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="前台账户管理",methods="前台账户管理-删除账户")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				custAccountMapper.deleteByAttribute("id", id, CustAccountFormMap.class);
			} catch (Exception e) {
				logger.error("删除账户失败", e);
				throw new SystemException("删除账户失败");
			}
		}
		return "success";
	}

	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("custAccount", custAccountMapper.findbyFrist("id", id, CustAccountFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/custAccount/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="前台账户管理",methods="前台账户管理-修改账户")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		CustAccountFormMap custAccountFormMap = getFormMap(CustAccountFormMap.class);
		try {
			String encodePwd = PwdUtil.encryptPassword((String)custAccountFormMap.get("password"));
			custAccountFormMap.set("password", encodePwd);
			custAccountMapper.editEntity(custAccountFormMap);
		} catch (Exception e) {
			logger.error("修改账户失败", e);
			e.printStackTrace();
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("accountList")
	public List<CustAccountFormMap> accountList() {
		CustAccountFormMap custAccountFormMap = getFormMap(CustAccountFormMap.class);
		List<CustAccountFormMap> custAccount = custAccountMapper.findCustAccountList(custAccountFormMap);
		return custAccount;
	}

	/**
	 * 验证手机号是否存在
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String mobile) {
		CustAccountFormMap custAccountFormMap = getFormMap(CustAccountFormMap.class);
		List<CustAccountFormMap> r = custAccountMapper.findByNames(custAccountFormMap);
		return r.size() == 0;
	}
}