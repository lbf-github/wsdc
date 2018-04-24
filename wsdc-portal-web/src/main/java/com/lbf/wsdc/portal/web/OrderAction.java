package com.lbf.wsdc.portal.web;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.util.CookieUtils;
import com.lbf.wsdc.pojo.po.Storemenu;
import com.lbf.wsdc.pojo.po.Uaddress;
import com.lbf.wsdc.pojo.po.Uorder;
import com.lbf.wsdc.pojo.vo.MyOrder;
import com.lbf.wsdc.service.AddressService;
import com.lbf.wsdc.service.OrderService;
import com.lbf.wsdc.service.StoreInfoService;
import com.lbf.wsdc.service.StoreMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/17
 * Time: 21:15
 * Version:V1.0
 */
@Controller
@RequestMapping("/order")
public class OrderAction {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private StoreInfoService storeInfoService;

    @Autowired
    private StoreMenuService storeMenuService;

    @Autowired
    private AddressService addressService;

    @RequestMapping("/makeOrder")
    @ResponseBody
    public MessageResult makeOrder(Long addressid, String cartId, String tokenId, HttpServletRequest request, HttpServletResponse response){

        MessageResult mr = null;
        try {
            mr = orderService.makeOeder(addressid,cartId,tokenId);

            CookieUtils.setCookie(request, response, "cart_token", "");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return mr;
    }


    @RequestMapping("/toOrder")
    public String toOrder(Model model,String tokenId){

        List<Uorder> orders = null;
        List<MyOrder> myOrdersList = null;
        try{
            myOrdersList = new ArrayList<>();
            orders = orderService.getOrders(tokenId);

            for(int i=0;i<orders.size();i++){
            MyOrder myOrder = new MyOrder();
                //获取商家名
                String storename = storeInfoService.getStoreInfoById(orders.get(i).getStoreid()).get(0).getStorename();
                //获取菜单名
                String orderfoodid = orders.get(i).getOrderfoodid();
                String orderfoodpricenum = orders.get(i).getOrderfoodpricenum();
                String[] num = orderfoodpricenum.split(",");
                String[] strMenuId = orderfoodid.split(",");
                String menuName = "";

                for(int j=0;j<strMenuId.length;j++){
                    if(strMenuId[i] != null && strMenuId[i]!=""){
                        Storemenu storeMenuById = storeMenuService.getStoreMenuById(Long.valueOf(strMenuId[j]));
                        if(menuName != null && menuName != ""){
                            menuName+=","+storeMenuById.getFoodname()+"*"+num[i];
                        }else{
                            menuName+=storeMenuById.getFoodname()+"*"+num[i];
                        }

                    }

                }
                //获取地址
                Uaddress uaddressById = addressService.getUaddressById(orders.get(i).getUaddressid().longValue());

                myOrder.setOrderid(orders.get(i).getOrderid());
                myOrder.setOrdercode(orders.get(i).getOrdercode());
                myOrder.setStoreName(storename);
                myOrder.setMenus(menuName);
                myOrder.setAddress(uaddressById.getUaddress());
                myOrder.setPrice(orders.get(i).getUnote());
                myOrder.setState(orders.get(i).getState());
                myOrder.setComment(orders.get(i).getComment());
                myOrdersList.add(myOrder);
            }

            model.addAttribute("myOrdersList",myOrdersList);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }


        return "home/order";
    }

    @RequestMapping("/toComment")
    public String toComment(Model model,Long orderId){

        model.addAttribute("orderId",orderId);

        return "home/comment";
    }

    @RequestMapping("/doComment")
    @ResponseBody
    public MessageResult doComment(Uorder uorder){
        MessageResult mr = null;
        try {

            mr = orderService.updateOrder(uorder);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }



        return mr;
    }

    @RequestMapping("/toStoreOrder")
    public String toStoreOrder(Model model,String tokenId){

        List<Uorder> orders = null;
        List<MyOrder> myOrdersList = null;
        try {
            myOrdersList = new ArrayList<>();
            orders = orderService.getStoreOrders(tokenId);

            for(int i=0;i<orders.size();i++){
                MyOrder myOrder = new MyOrder();
                //获取商家名
                String storename = storeInfoService.getStoreInfoById(orders.get(i).getStoreid()).get(0).getStorename();
                //获取菜单名
                String orderfoodid = orders.get(i).getOrderfoodid();
                String orderfoodpricenum = orders.get(i).getOrderfoodpricenum();
                String[] num = orderfoodpricenum.split(",");
                String[] strMenuId = orderfoodid.split(",");
                String menuName = "";

                for(int j=0;j<strMenuId.length;j++){
                    if(strMenuId[i] != null && strMenuId[i]!=""){
                        Storemenu storeMenuById = storeMenuService.getStoreMenuById(Long.valueOf(strMenuId[j]));
                        if(menuName != null && menuName != ""){
                            menuName+=","+storeMenuById.getFoodname()+"*"+num[i];
                        }else{
                            menuName+=storeMenuById.getFoodname()+"*"+num[i];
                        }

                    }

                }
                //获取地址
                Uaddress uaddressById = addressService.getUaddressById(orders.get(i).getUaddressid().longValue());
                myOrder.setUaccount(orders.get(i).getUaccount());
                myOrder.setOrderid(orders.get(i).getOrderid());
                myOrder.setOrdercode(orders.get(i).getOrdercode());
                myOrder.setStoreName(storename);
                myOrder.setMenus(menuName);
                myOrder.setAddress(uaddressById.getUaddress());
                myOrder.setPrice(orders.get(i).getUnote());
                myOrder.setState(orders.get(i).getState());
                myOrder.setComment(orders.get(i).getComment());
                myOrdersList.add(myOrder);
            }

            model.addAttribute("storeOrdersList",myOrdersList);


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return "shop/storeOrder";
    }


    /**
     * 确认订单
     */
    @RequestMapping("/doOrderSure")
    @ResponseBody
    public MessageResult doOrderSure(Long orderId){
        MessageResult mr = null;
        try{

            mr = orderService.updateState(orderId);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }
}
