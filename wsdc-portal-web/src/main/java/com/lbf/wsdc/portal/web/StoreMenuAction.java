package com.lbf.wsdc.portal.web;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.pojo.po.Storeinfo;
import com.lbf.wsdc.pojo.po.Storemenu;
import com.lbf.wsdc.service.IntroduceService;
import com.lbf.wsdc.service.StoreInfoService;
import com.lbf.wsdc.service.StoreMenuService;
import com.lbf.wsdc.service.StoreTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/5
 * Time: 22:19
 * Version:V1.0
 */
@Controller
@RequestMapping("/storeMenu")
public class StoreMenuAction {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IntroduceService introduceService;

    @Autowired
    private StoreTypeService storeTypeService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StoreInfoService storeInfoService;

    @Autowired
    private StoreMenuService storeMenuService;




    @RequestMapping("/page")
    public String getPageById(Model model,Long storeid){

        List<Storemenu> menuList=storeMenuService.getListById(storeid);
        List<Storeinfo> storeList=storeInfoService.getStoreInfoById(storeid);
        model.addAttribute("menuList", menuList);
        model.addAttribute("storeList", storeList.get(0));
//        request.getSession().setAttribute("client", clientService.GetByID(6));
//        request.getSession().setAttribute("left_thingtype2", thingtype2Service.Get());
        request.getSession().setAttribute("thingtype", storeTypeService.getStoreType());

        return "home/storeInfo";
    }

    @RequestMapping("/toMyMenu")
    public String toMyMenu(Model model,String tokenId){

        List<Storemenu> myStoreMenu = storeMenuService.getMyStoreMenu(tokenId);
        model.addAttribute("myStoreMenu",myStoreMenu);

        return "shop/myStoreMenu";
    }
    @RequestMapping("/toAddOrUpdate")
    public String toAddOrUpdate(Model model,Long menuid){
        if(menuid != null){
            Storemenu myMenu = storeMenuService.getStoreMenuById(menuid);
            model.addAttribute("myMenu",myMenu);
        }
        return "shop/editMenu";
    }
    @RequestMapping("/addOrUpdate")
    @ResponseBody
    public MessageResult addOrUpdate(Storemenu menu,String tokenId){
        MessageResult mr = null;
        if(menu.getMenuid() != null){
            //更新

            mr = storeMenuService.editMenu(menu);


        }else{
            //新增
            mr = storeMenuService.addMenu(menu,tokenId);
        }
        return mr;
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public MessageResult deleteMenu(Long menuId){
        MessageResult mr = null;
        try {
            mr = storeMenuService.deleteMenu(menuId);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }
}
