package com.lbf.wsdc.pojo.po;

import java.util.Date;

public class Sensitivet {
    private Long sensitiveid;

    private String sensitiveword;

    private Integer level;

    private Date date;

    public Long getSensitiveid() {
        return sensitiveid;
    }

    public void setSensitiveid(Long sensitiveid) {
        this.sensitiveid = sensitiveid;
    }

    public String getSensitiveword() {
        return sensitiveword;
    }

    public void setSensitiveword(String sensitiveword) {
        this.sensitiveword = sensitiveword == null ? null : sensitiveword.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}