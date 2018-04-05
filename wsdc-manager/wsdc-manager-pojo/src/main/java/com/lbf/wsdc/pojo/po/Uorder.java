package com.lbf.wsdc.pojo.po;

import java.util.Date;

public class Uorder {
    private Long orderid;

    private String uaccount;

    private String ordercode;

    private Long storeid;

    private Integer uaddressid;

    private String orderfoodid;

    private String orderfoodpricenum;

    private String unote;

    private Date orderdate;

    private String comment;

    private Integer state;

    private String commentTwo;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount == null ? null : uaccount.trim();
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public Integer getUaddressid() {
        return uaddressid;
    }

    public void setUaddressid(Integer uaddressid) {
        this.uaddressid = uaddressid;
    }

    public String getOrderfoodid() {
        return orderfoodid;
    }

    public void setOrderfoodid(String orderfoodid) {
        this.orderfoodid = orderfoodid == null ? null : orderfoodid.trim();
    }

    public String getOrderfoodpricenum() {
        return orderfoodpricenum;
    }

    public void setOrderfoodpricenum(String orderfoodpricenum) {
        this.orderfoodpricenum = orderfoodpricenum == null ? null : orderfoodpricenum.trim();
    }

    public String getUnote() {
        return unote;
    }

    public void setUnote(String unote) {
        this.unote = unote == null ? null : unote.trim();
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCommentTwo() {
        return commentTwo;
    }

    public void setCommentTwo(String commentTwo) {
        this.commentTwo = commentTwo == null ? null : commentTwo.trim();
    }
}