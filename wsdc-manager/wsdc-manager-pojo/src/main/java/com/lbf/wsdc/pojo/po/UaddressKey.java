package com.lbf.wsdc.pojo.po;

public class UaddressKey {
    private Integer uaddressid;

    private String uaccount;

    public Integer getUaddressid() {
        return uaddressid;
    }

    public void setUaddressid(Integer uaddressid) {
        this.uaddressid = uaddressid;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount == null ? null : uaccount.trim();
    }
}