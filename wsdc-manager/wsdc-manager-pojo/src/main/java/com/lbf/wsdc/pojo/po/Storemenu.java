package com.lbf.wsdc.pojo.po;

public class Storemenu extends StoremenuKey {
    private String foodname;

    private Float foodprices;

    private String foodtype;

    private String foodic;

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    public Float getFoodprices() {
        return foodprices;
    }

    public void setFoodprices(Float foodprices) {
        this.foodprices = foodprices;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype == null ? null : foodtype.trim();
    }

    public String getFoodic() {
        return foodic;
    }

    public void setFoodic(String foodic) {
        this.foodic = foodic == null ? null : foodic.trim();
    }
}