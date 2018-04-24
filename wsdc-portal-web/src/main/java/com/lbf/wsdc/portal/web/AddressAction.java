package com.lbf.wsdc.portal.web;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.pojo.po.Uaddress;
import com.lbf.wsdc.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/17
 * Time: 10:45
 * Version:V1.0
 */
@Controller
@RequestMapping("/address")
public class AddressAction {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AddressService addressService;

    /**
     * 跳转到我的地址页面
     * @return
     */
    @RequestMapping("/myAddresslist")
    public String toMyAddress(Model model,String tokenId){

        List<Uaddress> list =  addressService.getMyAddress(tokenId);
        model.addAttribute("addressList",list);

        return "home/address";
    }
    @RequestMapping("deleteAddress")
    @ResponseBody
    public MessageResult deleteAddressById(Long addressid){
        MessageResult mr = null;
        try {

        mr = addressService.deleteAddressByAccountId(addressid);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mr;
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Model model,Long addressId){

        Uaddress uaddress = addressService.getUaddressById(addressId);

        model.addAttribute("uaddress",uaddress);

        return "home/editAddress";

    }
    @RequestMapping("/toInsert")
    public String toInsert(){

        return "home/editAddress";

    }

    /**
     * 添加/修改地址
     */
    @RequestMapping("/updateAddress")
    @ResponseBody
    public MessageResult updateAddress(Uaddress uaddress,String tokenId){
        MessageResult mr = null;
        try {
            if(uaddress.getAddressid()!=null){
                mr = addressService.updateAddress(uaddress);
            }else{
                mr = addressService.insertAddress(uaddress,tokenId);
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }
}
