package com.lbf.wsdc.portal.web;

import com.lbf.wsdc.pojo.po.Introduce;
import com.lbf.wsdc.pojo.po.Storeinfo;
import com.lbf.wsdc.service.IntroduceService;
import com.lbf.wsdc.service.StoreInfoService;
import com.lbf.wsdc.service.StoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/5
 * Time: 20:46
 * Version:V1.0
 */
@Controller
@RequestMapping("/storeInfo")
public class StoreInfoAction {

    @Autowired
    public StoreInfoService storeInfoService;

    @Autowired
    public IntroduceService introduceService;

    @Autowired
    public StoreTypeService storeTypeService;

    @Autowired
    private HttpServletRequest request;


    @RequestMapping("/page")
    public List<Storeinfo> getStoreInfoList(){

        return storeInfoService.getStoreInfo();
    }

    @RequestMapping("/storeInfoList")
    public String getStoreInfoListBy(Model model, Integer stypeid){
        //查询状态为'正常'的商家
        List<Storeinfo> storeList=storeInfoService.getStoreInfoByTypeId(stypeid);
        Introduce intr = introduceService.GetByID(1);
        model.addAttribute("storeList", storeList);
        model.addAttribute("intr", intr);
        request.getSession().setAttribute("thingtype", storeTypeService.getStoreType());
        return "home/index";
    }

}
