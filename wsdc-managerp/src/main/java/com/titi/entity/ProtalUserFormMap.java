package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * User: Administrator
 * Date: 2018/3/28
 * Time: 9:42
 * Version:V1.0
 */
@TableSeg(tableName = "user", id="userid")
public class ProtalUserFormMap extends FormMap<String,Object> {
    private static final long serialVersionUID = 1L;
}
