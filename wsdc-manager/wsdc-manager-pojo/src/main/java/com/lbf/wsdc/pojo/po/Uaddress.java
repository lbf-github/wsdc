package com.lbf.wsdc.pojo.po;

public class Uaddress {
    private Long addressid;

    private Integer uaddressid;

    private String uaccount;

    private String ucontact;

    private Integer usex;

    private String utel;

    private String uaddress;

    private String utag;

    public Long getAddressid() {
        return addressid;
    }

    public void setAddressid(Long addressid) {
        this.addressid = addressid;
    }

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

    public String getUcontact() {
        return ucontact;
    }

    public void setUcontact(String ucontact) {
        this.ucontact = ucontact == null ? null : ucontact.trim();
    }

    public Integer getUsex() {
        return usex;
    }

    public void setUsex(Integer usex) {
        this.usex = usex;
    }

    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel == null ? null : utel.trim();
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress == null ? null : uaddress.trim();
    }

    public String getUtag() {
        return utag;
    }

    public void setUtag(String utag) {
        this.utag = utag == null ? null : utag.trim();
    }
}