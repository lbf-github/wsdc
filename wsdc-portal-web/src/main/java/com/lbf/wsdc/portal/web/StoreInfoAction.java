package com.lbf.wsdc.portal.web;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.util.CookieUtils;
import com.lbf.wsdc.pojo.po.Introduce;
import com.lbf.wsdc.pojo.po.Storeinfo;
import com.lbf.wsdc.pojo.po.Storetype;
import com.lbf.wsdc.service.IntroduceService;
import com.lbf.wsdc.service.StoreInfoService;
import com.lbf.wsdc.service.StoreTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private Logger logger= LoggerFactory.getLogger(this.getClass());

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

    @RequestMapping("/toRegisterStore")
    public String toRegisterStore(Model model,String tokenId){


        return "home/registerStore";
    }

    @RequestMapping("/doRegisterStore")
    @ResponseBody
    public MessageResult doRegisterStore(Storeinfo po,String tokenId,HttpServletRequest request, HttpServletResponse response){

        MessageResult mr = null;
        try {
            mr = storeInfoService.insetStoreInfo(po,tokenId);
            if (mr.isSuccess()) {
                //登录成功
                String token = mr.getData().toString();
                //写入cookie
                CookieUtils.setCookie(request, response, "login_token", token);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }

    @RequestMapping("/doUpdateStore")
    @ResponseBody
    public MessageResult doUpdateStore(Storeinfo po,String tokenId,Integer typeId){
        MessageResult mr = null;
        try {

            mr = storeInfoService.updateStoreInfo(po,tokenId,typeId);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }

    @RequestMapping("toSkipManager")
    public String toSkipManager(Model model,String tokenId){

        Storeinfo po = storeInfoService.getByTokenId(tokenId);
        model.addAttribute("storeinfo",po);
        List<Storetype> storeType = storeTypeService.getStoreType();
        model.addAttribute("storeType",storeType);


        return "shop/myStoreInfo";
    }


    //开店
    @RequestMapping("/openStore")
    @ResponseBody
    public MessageResult openStore(String tokenId){
        MessageResult mr = null;

        try {

            mr = storeInfoService.openOrCloseStore(1,tokenId);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }


        return mr;
    }

    //开店
    @RequestMapping("/closeStore")
    @ResponseBody
    public MessageResult closeStore(String tokenId){
        MessageResult mr = null;

        try {

            mr = storeInfoService.openOrCloseStore(0,tokenId);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }


        return mr;
    }
}
