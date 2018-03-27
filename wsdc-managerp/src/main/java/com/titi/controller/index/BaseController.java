package com.titi.controller.index;

import java.sql.Timestamp;
import java.util.Enumeration;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.titi.entity.AttachmentFormMap;
import com.titi.entity.ResFormMap;
import com.titi.entity.UserFormMap;
import com.titi.mapper.AttachmentMapper;
import com.titi.mapper.ResourcesMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import com.titi.util.Constants;
import com.titi.util.FormMap;

/**
 * 通用Controller
 * 
 * @author 陆彬峰
 */
public class BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Inject
	private ResourcesMapper resourcesMapper;

	@Inject
	private AttachmentMapper attachmentMapper;

	public PageView pageView = null;

	/** --编辑器图片信息过滤 -- **/
	protected String filterAttach(String htmlStr) {
		String htmlStr2  = htmlStr;
        Pattern p_image;   
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img,Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while(m_image.find()){
        	Matcher m  = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(m_image.group());
        	while(m.find()){
        		AttachmentFormMap attach = new AttachmentFormMap();
        		String url = m.group(1).replace(Constants.DOMAIN, "");
        		attach.set("url", url);
        		attach.set("create_date", new Timestamp(System.currentTimeMillis()));
        		try {
					attachmentMapper.addAttachmentEntity(attach);
					htmlStr2 = htmlStr2.replaceAll(m.group(1), "#attach_" + attach.get("id")); 
				} catch (Exception e) {
					logger.error("新增附件失败", e);
					e.printStackTrace();
				}
        	}
        }   
		return htmlStr2;
	}
	
	/** --编辑器图片地址反查 -- **/
	protected String getUESource(String htmlStr){
		String htmlStr2  = htmlStr;
		Pattern p_image;   
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img,Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while(m_image.find()){
        	Matcher m  = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(m_image.group());
        	while(m.find()){
        		String attachId = m.group(1).replace("#attach_","");
        		AttachmentFormMap attachment = attachmentMapper.findbyFrist("id", attachId, AttachmentFormMap.class);
        		htmlStr2 = htmlStr2.replaceAll("#attach_" + attachId, Constants.DOMAIN+attachment.get("url").toString()); 
        	}
        }  
		return htmlStr2;
	}

	public PageView getPageView(String pageNow, String pageSize, String orderby) {
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		if (Common.isEmpty(pageSize)) {
			pageSize = "10";
		}
		pageView.setPageSize(Integer.parseInt(pageSize));
		pageView.setOrderby(orderby);
		return pageView;
	}

	public <T> T toFormMap(T t, String pageNow, String pageSize, String orderby) {
		@SuppressWarnings("unchecked")
		FormMap<String, Object> formMap = (FormMap<String, Object>) t;
		formMap.put("paging", getPageView(pageNow, pageSize, orderby));
		return t;
	}

	/**
	 * 获取返回某一页面的按扭组, <br/>
	 * <b>author：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsplanyuan</b><br/>
	 * <b>date：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp2015-04-01</b><br/>
	 * <b>mod by：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspEkko</b><br/>
	 * <b>date：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp2015-09-07</b><br/>
	 * <b>version：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp3.0v</b>
	 * 
	 * @return Class<T>
	 * @throws Exception
	 */
	public List<ResFormMap> findByRes() {
		// 资源ID
		String id = getPara("id");
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap) Common.findUserSession(request);
		// user id
		int userId = userFormMap.getInt("id");
		ResFormMap resQueryForm = new ResFormMap();
		resQueryForm.put("parentId", id);
		resQueryForm.put("userId", userId);
		List<ResFormMap> rse = resourcesMapper.findRes(resQueryForm);
		// List<ResFormMap> rse = resourcesMapper.findByAttribute("parentId",
		// id, ResFormMap.class);
		for (ResFormMap resFormMap : rse) {
			Object o = resFormMap.get("description");
			if (o != null && !Common.isEmpty(o.toString())) {
				resFormMap
						.put("description", Common.stringtohtml(o.toString()));
			}
		}
		return rse;
	}

	/**
	 * 获取页面传递的某一个参数值, <br/>
	 * <b>author：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsplanyuan</b><br/>
	 * <b>date：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp2015-04-01</b><br/>
	 * <b>version：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp3.0v</b>
	 */
	public String getPara(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getParameter(key);
	}

	/**
	 * 获取页面传递的某一个数组值, <br/>
	 * <b>author：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsplanyuan</b><br/>
	 * <b>date：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp2015-04-01</b><br/>
	 * <b>version：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp3.0v</b>
	 * 
	 * @return Class<T>
	 * @throws Exception
	 */
	public String[] getParaValues(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getParameterValues(key);
	}

	/*
	 * @ModelAttribute 这个注解作用.每执行controllor前都会先执行这个方法
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2015-4-05
	 * 
	 * @param request
	 * 
	 * @throws Exception
	 * 
	 * @throws
	 */
	/*
	 * @ModelAttribute public void init(HttpServletRequest request){ String path
	 * = Common.BACKGROUND_PATH; Object ep =
	 * request.getSession().getAttribute("basePath"); if(ep!=null){
	 * if(!path.endsWith(ep.toString())){ Common.BACKGROUND_PATH =
	 * "/WEB-INF/jsp/background"+ep; } }
	 * 
	 * }
	 */

	/**
	 * 获取传递的所有参数, 反射实例化对象，再设置属性值 通过泛型回传对象. <br/>
	 * <b>author：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsplanyuan</b><br/>
	 * <b>date：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp2015-04-01</b><br/>
	 * <b>version：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp3.0v</b>
	 * 
	 * @return Class<T>
	 * @throws Exception
	 */
	public <T> T getFormMap(Class<T> clazz) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Enumeration<String> en = request.getParameterNames();
		T t = null;
		try {
			t = clazz.newInstance();
			@SuppressWarnings("unchecked")
			FormMap<String, Object> map = (FormMap<String, Object>) t;
			String order = "", sort = "";
			while (en.hasMoreElements()) {
				String nms = en.nextElement().toString();
				if (nms.endsWith("[]")) {
					String[] as = request.getParameterValues(nms);
					if (as != null && as.length != 0 && as.toString() != "[]") {
						String mname = t.getClass().getSimpleName()
								.toUpperCase();
						if (nms.toUpperCase().startsWith(mname)) {
							nms = nms.substring(nms.toUpperCase()
									.indexOf(mname) + 1);
							map.put(nms, as);
						}
					}
				} else {
					String as = request.getParameter(nms);
					if (!Common.isEmpty(as)) {
						String mname = t.getClass().getSimpleName()
								.toUpperCase();
						if (nms.toUpperCase().startsWith(mname)) {
							nms = nms.substring(mname.length() + 1);
							map.put(nms, as);
						}
						if (nms.toLowerCase().equals("column"))
							order = as;
						if (nms.toLowerCase().equals("sort"))
							sort = as;
					}
				}
			}
			if (!Common.isEmpty(order) && !Common.isEmpty(sort))
				map.put("orderby", " order by " + order + " " + sort);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 获取传递的所有参数, 再设置属性值 通过回传Map对象. <br/>
	 * <b>author：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsplijianning</b><br/>
	 * <b>date：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp2015-04-01</b><br/>
	 * <b>version：</b><br/>
	 * <b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp1.0v</b>
	 * 
	 * @return Class<T>
	 * @throws Exception
	 */
	public <T> T findHasHMap(Class<T> clazz) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Enumeration<String> en = request.getParameterNames();
		T t = null;
		try {
			t = clazz.newInstance();
			@SuppressWarnings("unchecked")
			FormMap<String, Object> map = (FormMap<String, Object>) t;
			while (en.hasMoreElements()) {
				String nms = en.nextElement().toString();
				if (!"_t".equals(nms)) {
					if (nms.endsWith("[]")) {
						String[] as = request.getParameterValues(nms);
						if (as != null && as.length != 0
								&& as.toString() != "[]") {
							map.put(nms, as);
						}
					} else {
						String as = request.getParameter(nms);
						if (!Common.isEmpty(as)) {
							map.put(nms, as);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}