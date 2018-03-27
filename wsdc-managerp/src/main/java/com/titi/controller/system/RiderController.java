package com.titi.controller.system;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.OrderFormMap;
import com.titi.entity.RiderFormMap;
import com.titi.entity.StationFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.RiderMapper;
import com.titi.mapper.StationMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import com.titi.util.EPlatform;
import com.titi.util.JsonUtils;
import com.titi.util.Loadfont;
import com.titi.util.OSinfo;
import com.titi.util.POIUtils;
import com.titi.util.qrCode.QRCodeUtil;

/**
 * 骑手信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/rider/")
public class RiderController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(RiderController.class);
		
	@Inject
	private RiderMapper riderMapper;//注入RiderMapper
	
	@Inject
	private StationMapper stationMapper;//注入StationMapper

	/**
	 * 跳转到骑手信息列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/rider/list";
	}

	/**
	 * 分页查询骑手信息
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
		RiderFormMap riderFormMap = getFormMap(RiderFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by entry_date desc";
		}
		riderFormMap.put("orderby", order);
		riderFormMap = toFormMap(riderFormMap, pageNow, pageSize,riderFormMap.getStr("orderby"));
		List<RiderFormMap> list = riderMapper.findRiderPage(riderFormMap);
        pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 跳转到 骑手新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/rider/add";
	}
	
	/**
	 * 新增骑手信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="骑手信息管理",methods="骑手信息管理-新增骑手")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			RiderFormMap riderFormMap = getFormMap(RiderFormMap.class);
			riderMapper.addEntity(riderFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("新增骑手信息失败", e);
			throw new SystemException("新增骑手信息失败");
		} 
		return "success";
	}

	/**
	 * 删除骑手信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="骑手信息管理",methods="骑手信息管理-删除骑手")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				riderMapper.deleteByAttribute("id", id, RiderFormMap.class);
			} catch (Exception e) {
				logger.error("删除骑手信息失败", e);
				throw new SystemException("删除骑手信息失败");
			}
		}
		return "success";
	}

	/**
	 * 跳转到编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("rider", riderMapper.findbyFrist("id", id, RiderFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/rider/edit";
	}

	/**
	 * 编辑骑手信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="骑手信息管理",methods="骑手信息管理-修改骑手")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		RiderFormMap riderFormMap = getFormMap(RiderFormMap.class);
		try {
			riderMapper.editEntity(riderFormMap);
		} catch (Exception e) {
			logger.error("修改骑手信息失败", e);
			throw new SystemException("修改骑手信息失败");
		}
		return "success";
	}
	
	/**
	 * 跳转到骑手二维码页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("viewQRCodeUI")
	public void viewDataUI(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 格式如 xx(id:xx)
		String rider_name = getPara("rider_name");
		rider_name = URLDecoder.decode(rider_name, "utf-8");
		String rider_id = getPara("rider_id");
		String rider_info = rider_name+"(id:"+rider_id+")";// 例如 张三(id:1) 用来生成 logo
		String saveName = rider_name+"ID"+rider_id+".jpg";//例如 张三(ID1) 用来另存为时作为文件名
		if(Common.isNotEmpty(rider_info)){
			//生成 指定文字的图片
			String text = rider_info;
			if(EPlatform.Windows.equals(OSinfo.getOSname())){
				File dir =new File("C://tmp");    
				//如果文件夹不存在则创建    
				if  (!dir .exists()  && !dir .isDirectory())      
				{       
					dir .mkdir();    
				} 
				//如果文件不存在则创建
				File file = new File("C://tmp/logo.jpg");
				if(!file.exists()){
					file.createNewFile(); 
				}
				QRCodeUtil.createImageForName(text, new Font("宋体", Font.BOLD, 18), file);
			}else if(EPlatform.Linux.equals(OSinfo.getOSname())){
				File dir =new File("/tmp");    
				//如果文件夹不存在则创建    
				if  (!dir .exists()  && !dir .isDirectory())      
				{       
					dir .mkdir();    
				} 
				
				File file = new File("/tmp/logo.jpg");
				String path = request.getServletContext().getRealPath("/")+"WEB-INF/font/SIMSUN.TTF";
				QRCodeUtil.createImageForName(text, Loadfont.Font2(path), file);
			}
		}
		if(Common.isNotEmpty(rider_id)){
			//String content = Constants.QRCODE_CONTENT+rider_id;
			RiderFormMap rider = riderMapper.findbyFrist("id", rider_id, RiderFormMap.class);
			StationFormMap station = stationMapper.findbyFrist("id", rider.get("station_id").toString(), StationFormMap.class);
			String content = station.get("url")+rider_id;
			
			String fileName = "";
			//带图标的二维码
			if(EPlatform.Windows.equals(OSinfo.getOSname())){
				fileName = QRCodeUtil.encode(content, "C://tmp/logo.jpg", "C://tmp", true);
				String filePath = "C://tmp/"+fileName;
				File file = new File(filePath);
				FileInputStream is = new FileInputStream(file);
				response.setHeader("Content-Type","image/jpeg");
				response.setHeader("Content-Disposition", "filename=" + java.net.URLEncoder.encode(saveName, "UTF-8"));
				ServletOutputStream out = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				//设置文件名
				out.flush();
				out.close();
				is.close();
				//删除生成的文件
				File logo = new File("C://tmp/logo.jpg");
				logo.delete();
				file.delete();
			}else if(EPlatform.Linux.equals(OSinfo.getOSname())){
				fileName = QRCodeUtil.encode(content, "/tmp/logo.jpg", "/tmp/", true);
				String filePath = "/tmp/"+fileName;
				File file = new File(filePath);
				FileInputStream is = new FileInputStream(file);
				response.setHeader("Content-Type","image/jpeg");
				response.setHeader("Content-Disposition", "filename=" + java.net.URLEncoder.encode(saveName, "UTF-8"));
				ServletOutputStream out = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				//设置文件名
				out.flush();
				out.close();
				is.close();
				
				//删除生成的文件
				File logo = new File("/tmp/logo.jpg");
				logo.delete();
				file.delete();
			}
		}
	}
	
	/** --跳转到关注骑手的用户订单列表页面 -- **/
	@RequestMapping("viewUserOrder")
	public String viewDataUI(Model model) {
		String rider_id = getPara("rider_id");
		if(Common.isNotEmpty(rider_id)){
			model.addAttribute("rider_id", rider_id);
		}
		return Common.BACKGROUND_PATH + "/system/rider/userOrderList";
	}
	
	/** --查询骑手推广的用户订单详情 -- **/
	@ResponseBody
	@RequestMapping("findUserOrderByPage")
	public PageView findUserOrderByPage( String pageNow,
			String pageSize,String column,String sort) {
		RiderFormMap riderFormMap = getFormMap(RiderFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		riderFormMap.put("orderby", order);
		riderFormMap = toFormMap(riderFormMap, pageNow, pageSize,riderFormMap.getStr("orderby"));
		riderFormMap.put("rider_id", getPara("rider_id"));
		pageView.setRecords(riderMapper.findOrderByRider(riderFormMap));
		return pageView;
	}
	
	/**
	 * 导出订单列表信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/export")
	public void download(HttpServletRequest request, HttpServletResponse response) {
		String fileName = "订单列表";
		RiderFormMap riderFormMap = findHasHMap(RiderFormMap.class);
		riderFormMap.put("rider_id", getPara("rider_id"));
		String exportData = riderFormMap.getStr("exportData");// 列表头的json字符串
		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);
		List<OrderFormMap> list = riderMapper.findOrderByRider(riderFormMap);
		POIUtils.exportToExcel(response, listMap, list, fileName);
	}
	
	/**
	 * 导出骑手列表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportRider")
	public void downloadRider(HttpServletRequest request, HttpServletResponse response) {
		String fileName = "骑手列表";
		RiderFormMap riderFormMap = findHasHMap(RiderFormMap.class);
		String exportData = riderFormMap.getStr("exportData");// 列表头的json字符串
		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);
		List<RiderFormMap> list = riderMapper.findRiderPage(riderFormMap);
		for(RiderFormMap rider : list){
			if("1".equals(rider.get("sex").toString())){
				rider.set("sex", "男");
			}else if("2".equals(rider.get("sex").toString())){
				rider.set("sex", "女");
			}else{
				rider.set("sex", "未知");
			}
		}
		POIUtils.exportToExcel(response, listMap, list, fileName);
	}
	
}