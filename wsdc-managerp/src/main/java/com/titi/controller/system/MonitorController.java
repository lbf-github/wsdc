package com.titi.controller.system;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.controller.index.BaseController;
import com.titi.entity.ServerInfoFormMap;
import com.titi.mapper.ServerInfoMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import com.titi.util.PropertiesUtils;
import com.titi.util.SystemInfo;

/**
 * 系统(服务器)信息action
 * @author 曾雄
 *
 */
@Controller
@RequestMapping("/monitor/")
public class MonitorController extends BaseController {
	
	@Inject
	private ServerInfoMapper serverInfoMapper ;
	
	//信息列表
	@RequestMapping("list")
	public String listUI() {
		return Common.BACKGROUND_PATH + "/system/monitor/list";
	}
	
	//分页查询
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow, String pageSize) {
		ServerInfoFormMap serverInfoFormMap = getFormMap(ServerInfoFormMap.class);
		serverInfoFormMap=toFormMap(serverInfoFormMap, pageNow, pageSize,serverInfoFormMap.getStr("orderby"));
        pageView.setRecords(serverInfoMapper.findByPage(serverInfoFormMap));
		return pageView;
	}
	
	//信息详情
	@RequestMapping("info")
	public String info(Model model) {
		model.addAttribute("cpu", PropertiesUtils.findPropertiesKey("cpu"));
		model.addAttribute("jvm", PropertiesUtils.findPropertiesKey("jvm"));
		model.addAttribute("ram", PropertiesUtils.findPropertiesKey("ram"));
		model.addAttribute("toEmail", PropertiesUtils.findPropertiesKey("toEmail"));
		return Common.BACKGROUND_PATH + "/system/monitor/info";
	}
	
	//信息首页
	@RequestMapping("monitor")
	public String monitor() {
		return Common.BACKGROUND_PATH + "/system/monitor/monitor";
	}
	
	//系统信息
	@RequestMapping("systemInfo")
	public String systemInfo(Model model) {
		model.addAttribute("systemInfo", SystemInfo.SystemProperty());
		return Common.BACKGROUND_PATH + "/system/monitor/systemInfo";
	}
	
	@ResponseBody
	@RequestMapping("usage")
	public ServerInfoFormMap usage(Model model) {
		return SystemInfo.usage(new Sigar());
	}
	
	/**
	 * 修改配置　
	 */
    @ResponseBody
	@RequestMapping("/modifySer")
    public Map<String, Object> modifySer(String key,String value) {
    	Map<String, Object> dataMap = new HashMap<String,Object>();
    	try {
		// 从输入流中读取属性列表（键和元素对）
    		PropertiesUtils.modifyProperties(key, value);
		} catch (Exception e) {
			dataMap.put("flag", false);
		}
    	dataMap.put("flag", true);
		return dataMap;
    }
}