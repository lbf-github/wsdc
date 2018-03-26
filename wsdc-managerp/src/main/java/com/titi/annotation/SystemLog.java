package com.titi.annotation;  
  
import java.lang.annotation.*;  
  
/** 
 * 自定义注解 拦截Controller
 * Controller中包含SystemLog注解的操作记录操作日志
 * @author 曾雄
 */  
  
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface SystemLog {  
  
	String module()  default "";  //模块名称 系统管理-用户管理－列表页面
	String methods()  default "";  //方法
    String description()  default "";  //描述
  
}  
  
