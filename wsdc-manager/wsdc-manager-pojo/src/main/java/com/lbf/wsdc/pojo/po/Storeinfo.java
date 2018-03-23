package com.lbf.wsdc.pojo.po;

public class Storeinfo {
    private Long storeid;

    private String storename;

    private String address;

    private String citycode;

    private String shophours;

    private String latlng;

    private Float startprice;

    private Float transportprice;

    private Integer transporttime;

    private String storelogo;

    private String notice;

    private String cheapennotice;

    private String newusernotice;

    private String tel;

    private String uaccount;

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    public String getShophours() {
        return shophours;
    }

    public void setShophours(String shophours) {
        this.shophours = shophours == null ? null : shophours.trim();
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng == null ? null : latlng.trim();
    }

    public Float getStartprice() {
        return startprice;
    }

    public void setStartprice(Float startprice) {
        this.startprice = startprice;
    }

    public Float getTransportprice() {
        return transportprice;
    }

    public void setTransportprice(Float transportprice) {
        this.transportprice = transportprice;
    }

    public Integer getTransporttime() {
        return transporttime;
    }

    public void setTransporttime(Integer transporttime) {
        this.transporttime = transporttime;
    }

    public String getStorelogo() {
        return storelogo;
    }

    public void setStorelogo(String storelogo) {
        this.storelogo = storelogo == null ? null : storelogo.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getCheapennotice() {
        return cheapennotice;
    }

    public void setCheapennotice(String cheapennotice) {
        this.cheapennotice = cheapennotice == null ? null : cheapennotice.trim();
    }

    public String getNewusernotice() {
        return newusernotice;
    }

    public void setNewusernotice(String newusernotice) {
        this.newusernotice = newusernotice == null ? null : newusernotice.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount == null ? null : uaccount.trim();
    }
}