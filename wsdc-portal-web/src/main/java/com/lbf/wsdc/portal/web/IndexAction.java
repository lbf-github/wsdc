package com.lbf.wsdc.portal.web;

import com.lbf.wsdc.pojo.po.Introduce;
import com.lbf.wsdc.pojo.po.Storeinfo;
import com.lbf.wsdc.service.IntroduceService;
import com.lbf.wsdc.service.StoreInfoService;
import com.lbf.wsdc.service.StoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: Administrator
 * Date: 2018/3/8
 * Time: 12:34
 * Version:V1.0
 */
@Controller
@Scope("prototype")
public class IndexAction {


    @Autowired
    public IntroduceService introduceService;

    @Autowired
    public StoreTypeService storeTypeService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StoreInfoService storeInfoService;

    @RequestMapping("/")
    public String index(){
        return "loginIndex";
    }

    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return page;
    }



    /**
     * 用户首页
     * @return
     */
    @RequestMapping("/index")
    public String homeIndex(Model model){
//        Pages p=new Pages();
//        p.setPagesize(6);
//        Thing t=new Thing();
//        t.setPage(p);
//
//        List<Thing> thing = thingService.Get(t);
        //查询状态为'正常'的商家
        List<Storeinfo> storeList=storeInfoService.getStoreInfo();
        Introduce intr = introduceService.GetByID(1);
        model.addAttribute("storeList", storeList);
        model.addAttribute("intr", intr);
//        request.getSession().setAttribute("client", clientService.GetByID(6));
//        request.getSession().setAttribute("left_thingtype2", thingtype2Service.Get());
        request.getSession().setAttribute("thingtype", storeTypeService.getStoreType());
//        model.addAttribute("thingtype", storeTypeService.getStoreType());
        return "home/index";

    }




}
