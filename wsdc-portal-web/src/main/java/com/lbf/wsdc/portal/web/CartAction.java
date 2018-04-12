package com.lbf.wsdc.portal.web;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.jedis.JedisClient;
import com.lbf.wsdc.common.util.CookieUtils;
import com.lbf.wsdc.common.util.JsonUtils;
import com.lbf.wsdc.pojo.po.Storemenu;
import com.lbf.wsdc.pojo.vo.ShoppingCart;
import com.lbf.wsdc.service.StoreMenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: Administrator
 * Date: 2018/4/6
 * Time: 14:56
 * Version:V1.0
 */
@Controller
@RequestMapping("/cart")
public class CartAction {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private StoreMenuService storeMenuService;


    @ResponseBody
    @RequestMapping(value = "/addCart",method = RequestMethod.POST)
    public Object addCart(Long menuid,String tokenId ,String cartId,HttpServletRequest request, HttpServletResponse response){
        MessageResult mr = null;
        try{
            mr = new MessageResult();
        //1.判断用户登录情况
        String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

        if(StringUtils.isBlank(userJson)){
            mr.setSuccess(false);
            mr.setMessage("请登录");
            return mr;
        }
        //2.判断是否存在购物车
//        List<Storemenu> menuListCart = null;
            List<ShoppingCart> menuListCart = null;
        String cartJson = jedisClient.get("Cart_TOKEN:" + cartId);
        if(StringUtils.isBlank(cartJson)){
//            menuListCart = new ArrayList<Storemenu>();
            menuListCart = new ArrayList<ShoppingCart>();
        }else{
//            menuListCart = JsonUtils.jsonToList(cartJson,Storemenu.class);
            menuListCart = JsonUtils.jsonToList(cartJson,ShoppingCart.class);
        }

        //3.获取菜品信息
        Storemenu menu = storeMenuService.getStoreMenuById(menuid);
        //新建购物车类，其中包含菜单所有信息，加上数量，总价等信息。现在饿了写不动了，待会完成
            boolean flag = false;
            if(menu != null){
                if(menuListCart.size()>0){
                for(int i=0;i<menuListCart.size();i++){
                    if(menuListCart.get(i).getMenuid() == menu.getMenuid()){
                        menuListCart.get(i).setNum(menuListCart.get(i).getNum()+1);
                        menuListCart.get(i).setTotalPrice(menuListCart.get(i).getFoodprices()+menuListCart.get(i).getTotalPrice());
                        flag = true;
                    }
                }
                }


                if(!flag){
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setMenuid(menu.getMenuid());
                    shoppingCart.setStoreid(menu.getStoreid());
                    shoppingCart.setFoodid(menu.getFoodid());
                    shoppingCart.setFoodname(menu.getFoodname());
                    shoppingCart.setFoodprices(menu.getFoodprices());
                    shoppingCart.setFoodic(menu.getFoodic());
                    shoppingCart.setNum(1);
                    shoppingCart.setTotalPrice(shoppingCart.getFoodprices());
                    menuListCart.add(shoppingCart);
                }

            }
        //-------------------------------------------------------------------------------


        //4.购物车信息存入redis
        String cart_token = UUID.randomUUID().toString();
        jedisClient.set("Cart_TOKEN:" + cart_token, JsonUtils.objectToJson(menuListCart));
        jedisClient.expire("Cart_TOKEN:" + cart_token, 1800);

        //5.更新Cookie
        CookieUtils.setCookie(request, response, "cart_token", cart_token);

        mr.setSuccess(true);
        mr.setMessage("成功加入购物车");

        }catch(Exception e){
        logger.error(e.getMessage(), e);
        e.printStackTrace();
        }

        return mr;
    }

    /**
     * 跳转到购物车页面
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/getCart")
    public String getbuying(Model model,String cartId) {
//        List<Buy> l=null;
//        if(request.getSession().getAttribute("buying")==null)
//            l=new ArrayList<Buy>();
//        else
//            l=(List<Buy>)request.getSession().getAttribute("buying");
//        int sum=0;
//        for(int i=0;i<l.size();i++)
//            sum+=l.get(i).getThing().getPrice();
//        m.addAttribute("buying",l);
//        m.addAttribute("sum",sum);
        List<ShoppingCart> menuListCart = null;
        String cartJson = jedisClient.get("Cart_TOKEN:" + cartId);
        if(StringUtils.isBlank(cartJson)){
            menuListCart = new ArrayList<ShoppingCart>();
        }else{
            menuListCart = JsonUtils.jsonToList(cartJson,ShoppingCart.class);
        }
        model.addAttribute("menuListCart",menuListCart);
        if(menuListCart.size()>0){
            Float sum = 0F;
            for(int i=0;i<menuListCart.size();i++){
                sum+=menuListCart.get(i).getTotalPrice();
            }
            model.addAttribute("sum",sum);
        }

        return "home/buying";
    }


}
