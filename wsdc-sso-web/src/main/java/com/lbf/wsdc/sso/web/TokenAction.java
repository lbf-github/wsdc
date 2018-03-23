package com.lbf.wsdc.sso.web;


import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Administrator
 * Date: 2017/12/3
 * Time: 12:50
 * Version:V1.0
 */
@Controller
public class TokenAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenService tokenService;

    @ResponseBody
    @RequestMapping("/user/token1/{tokenId}")
    public Object token1(@PathVariable("tokenId") String tokenId,String callback){
        MessageResult mr = null;
        try {
            mr = tokenService.getUserByToken(tokenId);
            if (StringUtils.isNotBlank(callback)) {
                //把结果封装成一个js语句响应
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(mr);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return mr;
    }
}
