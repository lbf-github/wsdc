package com.lbf.wsdc.pojo.po;

public class Storetype {
    private Integer stypeid;

    private String typename;

    private Integer supertypeid;

    public Integer getStypeid() {
        return stypeid;
    }

    public void setStypeid(Integer stypeid) {
        this.stypeid = stypeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Integer getSupertypeid() {
        return supertypeid;
    }

    public void setSupertypeid(Integer supertypeid) {
        this.supertypeid = supertypeid;
    }
}