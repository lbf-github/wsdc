package com.titi.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.DataDicFormMap;
import com.titi.mapper.DataDicMapper;
import com.titi.util.Common;
import com.titi.util.TreeObject;
import com.titi.util.TreeUtil;

/**
 * 数据字典信息 action
 * @author 曾雄
 *
 */
@Controller
@RequestMapping("/dataDic/")
public class DataDicController extends BaseController {
	
	//本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(DataDicController.class);
		
	@Inject
	private DataDicMapper dataDicMapper;//注入DataDicMapper

	/**
	 * 存放返回界面的model           
	 */
	@ResponseBody
	@RequestMapping("treelists")
	public DataDicFormMap findByPage(Model model) {
		DataDicFormMap dataDicFormMap = getFormMap(DataDicFormMap.class);
		List<DataDicFormMap> mps = dataDicMapper.findByNames(dataDicFormMap);
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (DataDicFormMap map : mps) {
			TreeObject ts = new TreeObject();
			//if(Integer.parseInt(map.get("ishide").toString()) == 0){
			Common.flushObject(ts, map);
			list.add(ts);
			//}
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
		dataDicFormMap = new DataDicFormMap();
		dataDicFormMap.put("treelists", ns);
		return dataDicFormMap;
	}

	@ResponseBody
	@RequestMapping("diclists")
	public List<TreeObject> diclists(Model model) {
		DataDicFormMap dataDicFormMap = getFormMap(DataDicFormMap.class);
		List<DataDicFormMap> mps = dataDicMapper.findByWhere(dataDicFormMap);
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (DataDicFormMap map : mps) {
			TreeObject ts = new TreeObject();
			Common.flushObject(ts, map);
			list.add(ts);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0, "　");
		return ns;
	}

	/**
	 * 存放返回界面的model
	 */
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dataDic/list";
	}

	/**
	 * 跳转到修改界面
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("dataDic", dataDicMapper.findbyFrist("id", id, DataDicFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/dataDic/edit";
	}

	/**
	 * 跳转到新增界面
	 * 
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/dataDic/add";
	}

	/**
	 * 添加数据字典信息
	 */
	@RequestMapping("addEntity")
	@ResponseBody
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="数据字典管理-新增数据")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() {
		DataDicFormMap dataDicFormMap = getFormMap(DataDicFormMap.class);
		Object o = dataDicFormMap.get("ishide");
		if(null == o){
			dataDicFormMap.set("ishide", "0");
		}
		try {
			dataDicMapper.addEntity(dataDicFormMap);
		} catch (Exception e) {
			logger.error("新增数据字典失败", e);
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 更新数据字典信息
	 * 
	 */
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="数据字典管理-修改数据")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity(Model model) {
		DataDicFormMap dataDicFormMap = getFormMap(DataDicFormMap.class);
		
		Object o = dataDicFormMap.get("ishide");
		if(null == o){
			dataDicFormMap.set("ishide", "0");
		}
		try {
			dataDicMapper.editEntity(dataDicFormMap);
		} catch (Exception e) {
			logger.error("编辑数据字典失败", e);
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 根据ID删除数据
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@SystemLog(module="系统管理",methods="数据字典管理-删除数据")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity(Model model) {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				dataDicMapper.deleteByAttribute("id", id, DataDicFormMap.class);
			} catch (Exception e) {
				logger.error("删除数据失败", e);
				e.printStackTrace();
			}
		};
		return "success";
	}

	@ResponseBody
	@RequestMapping("findDataDic")
	public List<DataDicFormMap> findDataDic() {
		DataDicFormMap dataDicFormMap = getFormMap(DataDicFormMap.class);
		List<DataDicFormMap> dataDic = dataDicMapper.findDic(dataDicFormMap);
		return dataDic;
	}

	/**
	 * 验证名称是否存在
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name,String resKey) {
		DataDicFormMap dataDicFormMap = getFormMap(DataDicFormMap.class);
		List<DataDicFormMap> r = dataDicMapper.findByNames(dataDicFormMap);
		return r.size() == 0;
	}
	
	/**
	 * 提供根据 resKey查询地区列表接口
	 * @param resKey
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findByResKey")
	public List<DataDicFormMap> findByResKey(String resKey){
		DataDicFormMap dataDicFormMap = getFormMap(DataDicFormMap.class);
		dataDicFormMap.put("where", "where reskey='"+resKey+"' and ishide=0 order by level desc");
		return dataDicMapper.findByWhere(dataDicFormMap);
	}
	
	/**
	 * 提供根据parentId查询子节点信息
	 * @param parentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findByPid")
	public List<DataDicFormMap> findByPid(String parentId){
		DataDicFormMap dataDicFormMap = getFormMap(DataDicFormMap.class);
		dataDicFormMap.put("where", "where parentId='"+parentId+"' and ishide=0 order by level desc");
		return dataDicMapper.findByWhere(dataDicFormMap);
	}

}