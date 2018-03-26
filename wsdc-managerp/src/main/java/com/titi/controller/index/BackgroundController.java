package com.titi.controller.index;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.titi.entity.ResFormMap;
import com.titi.entity.UserFormMap;
import com.titi.entity.UserLoginFormMap;
import com.titi.mapper.ResourcesMapper;
import com.titi.mapper.UserLoginMapper;
import com.titi.util.Common;
import com.titi.util.CusAccessObjectUtil;
import com.titi.util.TreeObject;
import com.titi.util.TreeUtil;
import com.mysql.jdbc.Connection;

/**
 * 进行管理后台框架界面的类
 * @author 曾雄
 */

@Controller
@RequestMapping("/")
public class BackgroundController extends BaseController {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(BackgroundController.class);

    @Inject
    private ResourcesMapper resourcesMapper;

    @Inject
    private UserLoginMapper userLoginMapper;

    @RequestMapping(value = "login", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public String login(HttpServletRequest request) {
        request.removeAttribute("error");
        return "/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String login(String username, String password, HttpServletRequest request) {
        try {
            if (!request.getMethod().equals("POST")) {
                request.setAttribute("error", "支持POST方法提交！");
            }
            if (Common.isEmpty(username) || Common.isEmpty(password)) {
                request.setAttribute("error", "用户名或密码不能为空！");
                return "/login";
            }
            // 想要得到 SecurityUtils.getSubject()　的对象．．访问地址必须跟shiro的拦截地址内．不然后会报空指针
            Subject user = SecurityUtils.getSubject();
            // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
            // 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
            // 当以上认证成功后会向下执行,认证失败会抛出异常
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                user.login(token);
            } catch (LockedAccountException lae) {
                token.clear();
                request.setAttribute("error", "用户已经被锁定不能登录，请与管理员联系！");
                return "/login";
            } catch (ExcessiveAttemptsException e) {
                token.clear();
                request.setAttribute("error", "账号：" + username + " 登录失败次数过多,锁定10分钟!");
                return "/login";
            } catch (AuthenticationException e) {
                token.clear();
                request.setAttribute("error", "用户或密码不正确！");
                return "/login";
            }
            UserLoginFormMap userLogin = new UserLoginFormMap();
            Session session = SecurityUtils.getSubject().getSession();
            userLogin.put("userId", session.getAttribute("userSessionId"));
            userLogin.put("accountName", username);
            //userLogin.put("loginIP", session.getHost());
            userLogin.put("loginIP", CusAccessObjectUtil.getIpAddress(request));
            userLoginMapper.addEntity(userLogin);
            request.removeAttribute("error");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "登录异常，请联系管理员！");
            return "/login";
        }
        return "redirect:index.shtml";
    }


    @RequestMapping("index")
    public String index(Model model) {
        // 获取登录的bean
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
        ResFormMap resFormMap = new ResFormMap();
        resFormMap.put("userId", userFormMap.get("id"));
        List<ResFormMap> mps = resourcesMapper.findRes(resFormMap);
        List<TreeObject> list = new ArrayList<>();
        for (ResFormMap map : mps) {
            TreeObject ts = new TreeObject();
            if(Integer.parseInt(map.get("ishide").toString()) == 0){
                Common.flushObject(ts, map);
                list.add(ts);
            }
        }
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);

        model.addAttribute("list", ns);
        // 登陆的信息回传页面
        model.addAttribute("userFormMap", userFormMap);
        return "/index";
    }

    @RequestMapping("menu")
    public String menu() {
        return "/framework/menu";
    }

    /**
     * 获取某个用户的权限资源
     */
    @RequestMapping("findAuthority")
    @ResponseBody
    public List<String> findAuthority() {
        return null;
    }

    @RequestMapping("download")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            logger.error("字符编码出错", e1);
            e1.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "\\"
                + "filezip\\";
        String downLoadPath = ctxPath + fileName;
        System.out.println(downLoadPath);
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error("数据流出错", e);
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("数据流出错", e);
                    e.printStackTrace();
                }
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        // 使用权限管理工具进行用户的退出，注销登录
        SecurityUtils.getSubject().logout(); // session  会销毁，在SessionListener监听session销毁，清理权限缓存
        return "redirect:login.shtml";
    }

    @RequestMapping("install")
    public String install() {
        try {
            Properties props = Resources.getResourceAsProperties("jdbc.properties");
            String url = props.getProperty("jdbc.url");
            String driver = props.getProperty("jdbc.driverClass");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            Class.forName(driver).newInstance();
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setErrorLogWriter(null);
            runner.setLogWriter(null);
            runner.runScript((new InputStreamReader(getClass().getResourceAsStream("/intall.sql"),"UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return "初始化失败！请联系管理员" + e;
        }
        return "/install";
    }

}
