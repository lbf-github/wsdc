package com.lbf.wsdc.pojo.po;

import java.util.ArrayList;
import java.util.List;

public class StoreinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StoreinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andStoreidIsNull() {
            addCriterion("storeId is null");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNotNull() {
            addCriterion("storeId is not null");
            return (Criteria) this;
        }

        public Criteria andStoreidEqualTo(Long value) {
            addCriterion("storeId =", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotEqualTo(Long value) {
            addCriterion("storeId <>", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThan(Long value) {
            addCriterion("storeId >", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThanOrEqualTo(Long value) {
            addCriterion("storeId >=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThan(Long value) {
            addCriterion("storeId <", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThanOrEqualTo(Long value) {
            addCriterion("storeId <=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidIn(List<Long> values) {
            addCriterion("storeId in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotIn(List<Long> values) {
            addCriterion("storeId not in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidBetween(Long value1, Long value2) {
            addCriterion("storeId between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotBetween(Long value1, Long value2) {
            addCriterion("storeId not between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andStorenameIsNull() {
            addCriterion("storeName is null");
            return (Criteria) this;
        }

        public Criteria andStorenameIsNotNull() {
            addCriterion("storeName is not null");
            return (Criteria) this;
        }

        public Criteria andStorenameEqualTo(String value) {
            addCriterion("storeName =", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotEqualTo(String value) {
            addCriterion("storeName <>", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameGreaterThan(String value) {
            addCriterion("storeName >", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameGreaterThanOrEqualTo(String value) {
            addCriterion("storeName >=", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLessThan(String value) {
            addCriterion("storeName <", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLessThanOrEqualTo(String value) {
            addCriterion("storeName <=", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLike(String value) {
            addCriterion("storeName like", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotLike(String value) {
            addCriterion("storeName not like", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameIn(List<String> values) {
            addCriterion("storeName in", values, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotIn(List<String> values) {
            addCriterion("storeName not in", values, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameBetween(String value1, String value2) {
            addCriterion("storeName between", value1, value2, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotBetween(String value1, String value2) {
            addCriterion("storeName not between", value1, value2, "storename");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andCitycodeIsNull() {
            addCriterion("citycode is null");
            return (Criteria) this;
        }

        public Criteria andCitycodeIsNotNull() {
            addCriterion("citycode is not null");
            return (Criteria) this;
        }

        public Criteria andCitycodeEqualTo(String value) {
            addCriterion("citycode =", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotEqualTo(String value) {
            addCriterion("citycode <>", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeGreaterThan(String value) {
            addCriterion("citycode >", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeGreaterThanOrEqualTo(String value) {
            addCriterion("citycode >=", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLessThan(String value) {
            addCriterion("citycode <", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLessThanOrEqualTo(String value) {
            addCriterion("citycode <=", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLike(String value) {
            addCriterion("citycode like", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotLike(String value) {
            addCriterion("citycode not like", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeIn(List<String> values) {
            addCriterion("citycode in", values, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotIn(List<String> values) {
            addCriterion("citycode not in", values, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeBetween(String value1, String value2) {
            addCriterion("citycode between", value1, value2, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotBetween(String value1, String value2) {
            addCriterion("citycode not between", value1, value2, "citycode");
            return (Criteria) this;
        }

        public Criteria andShophoursIsNull() {
            addCriterion("shophours is null");
            return (Criteria) this;
        }

        public Criteria andShophoursIsNotNull() {
            addCriterion("shophours is not null");
            return (Criteria) this;
        }

        public Criteria andShophoursEqualTo(String value) {
            addCriterion("shophours =", value, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursNotEqualTo(String value) {
            addCriterion("shophours <>", value, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursGreaterThan(String value) {
            addCriterion("shophours >", value, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursGreaterThanOrEqualTo(String value) {
            addCriterion("shophours >=", value, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursLessThan(String value) {
            addCriterion("shophours <", value, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursLessThanOrEqualTo(String value) {
            addCriterion("shophours <=", value, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursLike(String value) {
            addCriterion("shophours like", value, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursNotLike(String value) {
            addCriterion("shophours not like", value, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursIn(List<String> values) {
            addCriterion("shophours in", values, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursNotIn(List<String> values) {
            addCriterion("shophours not in", values, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursBetween(String value1, String value2) {
            addCriterion("shophours between", value1, value2, "shophours");
            return (Criteria) this;
        }

        public Criteria andShophoursNotBetween(String value1, String value2) {
            addCriterion("shophours not between", value1, value2, "shophours");
            return (Criteria) this;
        }

        public Criteria andLatlngIsNull() {
            addCriterion("latlng is null");
            return (Criteria) this;
        }

        public Criteria andLatlngIsNotNull() {
            addCriterion("latlng is not null");
            return (Criteria) this;
        }

        public Criteria andLatlngEqualTo(String value) {
            addCriterion("latlng =", value, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngNotEqualTo(String value) {
            addCriterion("latlng <>", value, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngGreaterThan(String value) {
            addCriterion("latlng >", value, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngGreaterThanOrEqualTo(String value) {
            addCriterion("latlng >=", value, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngLessThan(String value) {
            addCriterion("latlng <", value, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngLessThanOrEqualTo(String value) {
            addCriterion("latlng <=", value, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngLike(String value) {
            addCriterion("latlng like", value, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngNotLike(String value) {
            addCriterion("latlng not like", value, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngIn(List<String> values) {
            addCriterion("latlng in", values, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngNotIn(List<String> values) {
            addCriterion("latlng not in", values, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngBetween(String value1, String value2) {
            addCriterion("latlng between", value1, value2, "latlng");
            return (Criteria) this;
        }

        public Criteria andLatlngNotBetween(String value1, String value2) {
            addCriterion("latlng not between", value1, value2, "latlng");
            return (Criteria) this;
        }

        public Criteria andStartpriceIsNull() {
            addCriterion("startprice is null");
            return (Criteria) this;
        }

        public Criteria andStartpriceIsNotNull() {
            addCriterion("startprice is not null");
            return (Criteria) this;
        }

        public Criteria andStartpriceEqualTo(Float value) {
            addCriterion("startprice =", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceNotEqualTo(Float value) {
            addCriterion("startprice <>", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceGreaterThan(Float value) {
            addCriterion("startprice >", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceGreaterThanOrEqualTo(Float value) {
            addCriterion("startprice >=", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceLessThan(Float value) {
            addCriterion("startprice <", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceLessThanOrEqualTo(Float value) {
            addCriterion("startprice <=", value, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceIn(List<Float> values) {
            addCriterion("startprice in", values, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceNotIn(List<Float> values) {
            addCriterion("startprice not in", values, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceBetween(Float value1, Float value2) {
            addCriterion("startprice between", value1, value2, "startprice");
            return (Criteria) this;
        }

        public Criteria andStartpriceNotBetween(Float value1, Float value2) {
            addCriterion("startprice not between", value1, value2, "startprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceIsNull() {
            addCriterion("transportprice is null");
            return (Criteria) this;
        }

        public Criteria andTransportpriceIsNotNull() {
            addCriterion("transportprice is not null");
            return (Criteria) this;
        }

        public Criteria andTransportpriceEqualTo(Float value) {
            addCriterion("transportprice =", value, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceNotEqualTo(Float value) {
            addCriterion("transportprice <>", value, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceGreaterThan(Float value) {
            addCriterion("transportprice >", value, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceGreaterThanOrEqualTo(Float value) {
            addCriterion("transportprice >=", value, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceLessThan(Float value) {
            addCriterion("transportprice <", value, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceLessThanOrEqualTo(Float value) {
            addCriterion("transportprice <=", value, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceIn(List<Float> values) {
            addCriterion("transportprice in", values, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceNotIn(List<Float> values) {
            addCriterion("transportprice not in", values, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceBetween(Float value1, Float value2) {
            addCriterion("transportprice between", value1, value2, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransportpriceNotBetween(Float value1, Float value2) {
            addCriterion("transportprice not between", value1, value2, "transportprice");
            return (Criteria) this;
        }

        public Criteria andTransporttimeIsNull() {
            addCriterion("transporttime is null");
            return (Criteria) this;
        }

        public Criteria andTransporttimeIsNotNull() {
            addCriterion("transporttime is not null");
            return (Criteria) this;
        }

        public Criteria andTransporttimeEqualTo(Integer value) {
            addCriterion("transporttime =", value, "transporttime");
            return (Criteria) this;
        }

        public Criteria andTransporttimeNotEqualTo(Integer value) {
            addCriterion("transporttime <>", value, "transporttime");
            return (Criteria) this;
        }

        public Criteria andTransporttimeGreaterThan(Integer value) {
            addCriterion("transporttime >", value, "transporttime");
            return (Criteria) this;
        }

        public Criteria andTransporttimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("transporttime >=", value, "transporttime");
            return (Criteria) this;
        }

        public Criteria andTransporttimeLessThan(Integer value) {
            addCriterion("transporttime <", value, "transporttime");
            return (Criteria) this;
        }

        public Criteria andTransporttimeLessThanOrEqualTo(Integer value) {
            addCriterion("transporttime <=", value, "transporttime");
            return (Criteria) this;
        }

        public Criteria andTransporttimeIn(List<Integer> values) {
            addCriterion("transporttime in", values, "transporttime");
            return (Criteria) this;
        }

        public Criteria andTransporttimeNotIn(List<Integer> values) {
            addCriterion("transporttime not in", values, "transporttime");
            return (Criteria) this;
        }

        public Criteria andTransporttimeBetween(Integer value1, Integer value2) {
            addCriterion("transporttime between", value1, value2, "transporttime");
            return (Criteria) this;
        }

        public Criteria andTransporttimeNotBetween(Integer value1, Integer value2) {
            addCriterion("transporttime not between", value1, value2, "transporttime");
            return (Criteria) this;
        }

        public Criteria andStorelogoIsNull() {
            addCriterion("storelogo is null");
            return (Criteria) this;
        }

        public Criteria andStorelogoIsNotNull() {
            addCriterion("storelogo is not null");
            return (Criteria) this;
        }

        public Criteria andStorelogoEqualTo(String value) {
            addCriterion("storelogo =", value, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoNotEqualTo(String value) {
            addCriterion("storelogo <>", value, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoGreaterThan(String value) {
            addCriterion("storelogo >", value, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoGreaterThanOrEqualTo(String value) {
            addCriterion("storelogo >=", value, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoLessThan(String value) {
            addCriterion("storelogo <", value, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoLessThanOrEqualTo(String value) {
            addCriterion("storelogo <=", value, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoLike(String value) {
            addCriterion("storelogo like", value, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoNotLike(String value) {
            addCriterion("storelogo not like", value, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoIn(List<String> values) {
            addCriterion("storelogo in", values, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoNotIn(List<String> values) {
            addCriterion("storelogo not in", values, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoBetween(String value1, String value2) {
            addCriterion("storelogo between", value1, value2, "storelogo");
            return (Criteria) this;
        }

        public Criteria andStorelogoNotBetween(String value1, String value2) {
            addCriterion("storelogo not between", value1, value2, "storelogo");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNull() {
            addCriterion("notice is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNotNull() {
            addCriterion("notice is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeEqualTo(String value) {
            addCriterion("notice =", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotEqualTo(String value) {
            addCriterion("notice <>", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThan(String value) {
            addCriterion("notice >", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("notice >=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThan(String value) {
            addCriterion("notice <", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThanOrEqualTo(String value) {
            addCriterion("notice <=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLike(String value) {
            addCriterion("notice like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotLike(String value) {
            addCriterion("notice not like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeIn(List<String> values) {
            addCriterion("notice in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotIn(List<String> values) {
            addCriterion("notice not in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeBetween(String value1, String value2) {
            addCriterion("notice between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotBetween(String value1, String value2) {
            addCriterion("notice not between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeIsNull() {
            addCriterion("cheapennotice is null");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeIsNotNull() {
            addCriterion("cheapennotice is not null");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeEqualTo(String value) {
            addCriterion("cheapennotice =", value, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeNotEqualTo(String value) {
            addCriterion("cheapennotice <>", value, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeGreaterThan(String value) {
            addCriterion("cheapennotice >", value, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeGreaterThanOrEqualTo(String value) {
            addCriterion("cheapennotice >=", value, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeLessThan(String value) {
            addCriterion("cheapennotice <", value, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeLessThanOrEqualTo(String value) {
            addCriterion("cheapennotice <=", value, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeLike(String value) {
            addCriterion("cheapennotice like", value, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeNotLike(String value) {
            addCriterion("cheapennotice not like", value, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeIn(List<String> values) {
            addCriterion("cheapennotice in", values, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeNotIn(List<String> values) {
            addCriterion("cheapennotice not in", values, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeBetween(String value1, String value2) {
            addCriterion("cheapennotice between", value1, value2, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andCheapennoticeNotBetween(String value1, String value2) {
            addCriterion("cheapennotice not between", value1, value2, "cheapennotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeIsNull() {
            addCriterion("newusernotice is null");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeIsNotNull() {
            addCriterion("newusernotice is not null");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeEqualTo(String value) {
            addCriterion("newusernotice =", value, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeNotEqualTo(String value) {
            addCriterion("newusernotice <>", value, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeGreaterThan(String value) {
            addCriterion("newusernotice >", value, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeGreaterThanOrEqualTo(String value) {
            addCriterion("newusernotice >=", value, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeLessThan(String value) {
            addCriterion("newusernotice <", value, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeLessThanOrEqualTo(String value) {
            addCriterion("newusernotice <=", value, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeLike(String value) {
            addCriterion("newusernotice like", value, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeNotLike(String value) {
            addCriterion("newusernotice not like", value, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeIn(List<String> values) {
            addCriterion("newusernotice in", values, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeNotIn(List<String> values) {
            addCriterion("newusernotice not in", values, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeBetween(String value1, String value2) {
            addCriterion("newusernotice between", value1, value2, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andNewusernoticeNotBetween(String value1, String value2) {
            addCriterion("newusernotice not between", value1, value2, "newusernotice");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andUaccountIsNull() {
            addCriterion("uaccount is null");
            return (Criteria) this;
        }

        public Criteria andUaccountIsNotNull() {
            addCriterion("uaccount is not null");
            return (Criteria) this;
        }

        public Criteria andUaccountEqualTo(String value) {
            addCriterion("uaccount =", value, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountNotEqualTo(String value) {
            addCriterion("uaccount <>", value, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountGreaterThan(String value) {
            addCriterion("uaccount >", value, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountGreaterThanOrEqualTo(String value) {
            addCriterion("uaccount >=", value, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountLessThan(String value) {
            addCriterion("uaccount <", value, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountLessThanOrEqualTo(String value) {
            addCriterion("uaccount <=", value, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountLike(String value) {
            addCriterion("uaccount like", value, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountNotLike(String value) {
            addCriterion("uaccount not like", value, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountIn(List<String> values) {
            addCriterion("uaccount in", values, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountNotIn(List<String> values) {
            addCriterion("uaccount not in", values, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountBetween(String value1, String value2) {
            addCriterion("uaccount between", value1, value2, "uaccount");
            return (Criteria) this;
        }

        public Criteria andUaccountNotBetween(String value1, String value2) {
            addCriterion("uaccount not between", value1, value2, "uaccount");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}