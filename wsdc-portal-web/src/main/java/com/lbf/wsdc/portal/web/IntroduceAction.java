package com.lbf.wsdc.portal.web;


import com.lbf.wsdc.pojo.po.Introduce;
import com.lbf.wsdc.portal.utils.ResponseUtil;
import com.lbf.wsdc.service.IntroduceService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/introduce")
public class IntroduceAction {
	private IntroduceService introduceService;

	public IntroduceService getIntroduceService() {
		return introduceService;
	}

	@Autowired
	public void setIntroduceService(IntroduceService introduceService) {
		this.introduceService = introduceService;
	}

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@SuppressWarnings("finally")
	@RequestMapping("/introduce_add")
	public String add(Introduce intr) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println(request.getParameter("login"));
			intr.setContent(request.getParameter("content"));
			String id=request.getParameter("id").trim().length()==0?"0":request.getParameter("id");
			intr.setId(Integer.parseInt(id));
			int count=introduceService.Edit(intr);
			if(count>0)
			{
				map.put("mgf", "操作成功");
				map.put("success", true);
			}
			else
			{
				map.put("mgf", "操作失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("mgf", "错误："+e.getMessage());
			map.put("success", false);
		} 
		String result = new JSONObject(map).toString();
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/introduce_list")
	public String Get() {
		try {
			List<Introduce> list = introduceService.Get();
			System.out.println(list);
			request.setAttribute("list", list);
			return "admin/introduce";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/call")
	public String Call() {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			Introduce intr = introduceService.GetByID(id);
			request.setAttribute("introduce", intr);
			return "home/introduce";
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("/introduce_messhow")
	public String GetByID() {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			Introduce intr = introduceService.GetByID(id);
			request.setAttribute("introduce", intr);
			return "admin/addintroduce";
		} catch (Exception e) {
			return null;
		}
	}
	
	
}