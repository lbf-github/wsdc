package com.lbf.wsdc.pojo.po;

import java.util.ArrayList;
import java.util.List;

public class UaddressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UaddressExample() {
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

        public Criteria andUaddressidIsNull() {
            addCriterion("uaddressid is null");
            return (Criteria) this;
        }

        public Criteria andUaddressidIsNotNull() {
            addCriterion("uaddressid is not null");
            return (Criteria) this;
        }

        public Criteria andUaddressidEqualTo(Integer value) {
            addCriterion("uaddressid =", value, "uaddressid");
            return (Criteria) this;
        }

        public Criteria andUaddressidNotEqualTo(Integer value) {
            addCriterion("uaddressid <>", value, "uaddressid");
            return (Criteria) this;
        }

        public Criteria andUaddressidGreaterThan(Integer value) {
            addCriterion("uaddressid >", value, "uaddressid");
            return (Criteria) this;
        }

        public Criteria andUaddressidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uaddressid >=", value, "uaddressid");
            return (Criteria) this;
        }

        public Criteria andUaddressidLessThan(Integer value) {
            addCriterion("uaddressid <", value, "uaddressid");
            return (Criteria) this;
        }

        public Criteria andUaddressidLessThanOrEqualTo(Integer value) {
            addCriterion("uaddressid <=", value, "uaddressid");
            return (Criteria) this;
        }

        public Criteria andUaddressidIn(List<Integer> values) {
            addCriterion("uaddressid in", values, "uaddressid");
            return (Criteria) this;
        }

        public Criteria andUaddressidNotIn(List<Integer> values) {
            addCriterion("uaddressid not in", values, "uaddressid");
            return (Criteria) this;
        }

        public Criteria andUaddressidBetween(Integer value1, Integer value2) {
            addCriterion("uaddressid between", value1, value2, "uaddressid");
            return (Criteria) this;
        }

        public Criteria andUaddressidNotBetween(Integer value1, Integer value2) {
            addCriterion("uaddressid not between", value1, value2, "uaddressid");
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

        public Criteria andUcontactIsNull() {
            addCriterion("ucontact is null");
            return (Criteria) this;
        }

        public Criteria andUcontactIsNotNull() {
            addCriterion("ucontact is not null");
            return (Criteria) this;
        }

        public Criteria andUcontactEqualTo(String value) {
            addCriterion("ucontact =", value, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactNotEqualTo(String value) {
            addCriterion("ucontact <>", value, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactGreaterThan(String value) {
            addCriterion("ucontact >", value, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactGreaterThanOrEqualTo(String value) {
            addCriterion("ucontact >=", value, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactLessThan(String value) {
            addCriterion("ucontact <", value, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactLessThanOrEqualTo(String value) {
            addCriterion("ucontact <=", value, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactLike(String value) {
            addCriterion("ucontact like", value, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactNotLike(String value) {
            addCriterion("ucontact not like", value, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactIn(List<String> values) {
            addCriterion("ucontact in", values, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactNotIn(List<String> values) {
            addCriterion("ucontact not in", values, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactBetween(String value1, String value2) {
            addCriterion("ucontact between", value1, value2, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUcontactNotBetween(String value1, String value2) {
            addCriterion("ucontact not between", value1, value2, "ucontact");
            return (Criteria) this;
        }

        public Criteria andUsexIsNull() {
            addCriterion("usex is null");
            return (Criteria) this;
        }

        public Criteria andUsexIsNotNull() {
            addCriterion("usex is not null");
            return (Criteria) this;
        }

        public Criteria andUsexEqualTo(Integer value) {
            addCriterion("usex =", value, "usex");
            return (Criteria) this;
        }

        public Criteria andUsexNotEqualTo(Integer value) {
            addCriterion("usex <>", value, "usex");
            return (Criteria) this;
        }

        public Criteria andUsexGreaterThan(Integer value) {
            addCriterion("usex >", value, "usex");
            return (Criteria) this;
        }

        public Criteria andUsexGreaterThanOrEqualTo(Integer value) {
            addCriterion("usex >=", value, "usex");
            return (Criteria) this;
        }

        public Criteria andUsexLessThan(Integer value) {
            addCriterion("usex <", value, "usex");
            return (Criteria) this;
        }

        public Criteria andUsexLessThanOrEqualTo(Integer value) {
            addCriterion("usex <=", value, "usex");
            return (Criteria) this;
        }

        public Criteria andUsexIn(List<Integer> values) {
            addCriterion("usex in", values, "usex");
            return (Criteria) this;
        }

        public Criteria andUsexNotIn(List<Integer> values) {
            addCriterion("usex not in", values, "usex");
            return (Criteria) this;
        }

        public Criteria andUsexBetween(Integer value1, Integer value2) {
            addCriterion("usex between", value1, value2, "usex");
            return (Criteria) this;
        }

        public Criteria andUsexNotBetween(Integer value1, Integer value2) {
            addCriterion("usex not between", value1, value2, "usex");
            return (Criteria) this;
        }

        public Criteria andUtelIsNull() {
            addCriterion("utel is null");
            return (Criteria) this;
        }

        public Criteria andUtelIsNotNull() {
            addCriterion("utel is not null");
            return (Criteria) this;
        }

        public Criteria andUtelEqualTo(String value) {
            addCriterion("utel =", value, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelNotEqualTo(String value) {
            addCriterion("utel <>", value, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelGreaterThan(String value) {
            addCriterion("utel >", value, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelGreaterThanOrEqualTo(String value) {
            addCriterion("utel >=", value, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelLessThan(String value) {
            addCriterion("utel <", value, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelLessThanOrEqualTo(String value) {
            addCriterion("utel <=", value, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelLike(String value) {
            addCriterion("utel like", value, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelNotLike(String value) {
            addCriterion("utel not like", value, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelIn(List<String> values) {
            addCriterion("utel in", values, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelNotIn(List<String> values) {
            addCriterion("utel not in", values, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelBetween(String value1, String value2) {
            addCriterion("utel between", value1, value2, "utel");
            return (Criteria) this;
        }

        public Criteria andUtelNotBetween(String value1, String value2) {
            addCriterion("utel not between", value1, value2, "utel");
            return (Criteria) this;
        }

        public Criteria andUaddressIsNull() {
            addCriterion("uaddress is null");
            return (Criteria) this;
        }

        public Criteria andUaddressIsNotNull() {
            addCriterion("uaddress is not null");
            return (Criteria) this;
        }

        public Criteria andUaddressEqualTo(String value) {
            addCriterion("uaddress =", value, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressNotEqualTo(String value) {
            addCriterion("uaddress <>", value, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressGreaterThan(String value) {
            addCriterion("uaddress >", value, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressGreaterThanOrEqualTo(String value) {
            addCriterion("uaddress >=", value, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressLessThan(String value) {
            addCriterion("uaddress <", value, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressLessThanOrEqualTo(String value) {
            addCriterion("uaddress <=", value, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressLike(String value) {
            addCriterion("uaddress like", value, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressNotLike(String value) {
            addCriterion("uaddress not like", value, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressIn(List<String> values) {
            addCriterion("uaddress in", values, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressNotIn(List<String> values) {
            addCriterion("uaddress not in", values, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressBetween(String value1, String value2) {
            addCriterion("uaddress between", value1, value2, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUaddressNotBetween(String value1, String value2) {
            addCriterion("uaddress not between", value1, value2, "uaddress");
            return (Criteria) this;
        }

        public Criteria andUtagIsNull() {
            addCriterion("utag is null");
            return (Criteria) this;
        }

        public Criteria andUtagIsNotNull() {
            addCriterion("utag is not null");
            return (Criteria) this;
        }

        public Criteria andUtagEqualTo(String value) {
            addCriterion("utag =", value, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagNotEqualTo(String value) {
            addCriterion("utag <>", value, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagGreaterThan(String value) {
            addCriterion("utag >", value, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagGreaterThanOrEqualTo(String value) {
            addCriterion("utag >=", value, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagLessThan(String value) {
            addCriterion("utag <", value, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagLessThanOrEqualTo(String value) {
            addCriterion("utag <=", value, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagLike(String value) {
            addCriterion("utag like", value, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagNotLike(String value) {
            addCriterion("utag not like", value, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagIn(List<String> values) {
            addCriterion("utag in", values, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagNotIn(List<String> values) {
            addCriterion("utag not in", values, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagBetween(String value1, String value2) {
            addCriterion("utag between", value1, value2, "utag");
            return (Criteria) this;
        }

        public Criteria andUtagNotBetween(String value1, String value2) {
            addCriterion("utag not between", value1, value2, "utag");
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