package com.lbf.wsdc.pojo.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UorderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UorderExample() {
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

        public Criteria andOrdercodeIsNull() {
            addCriterion("ordercode is null");
            return (Criteria) this;
        }

        public Criteria andOrdercodeIsNotNull() {
            addCriterion("ordercode is not null");
            return (Criteria) this;
        }

        public Criteria andOrdercodeEqualTo(String value) {
            addCriterion("ordercode =", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotEqualTo(String value) {
            addCriterion("ordercode <>", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeGreaterThan(String value) {
            addCriterion("ordercode >", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeGreaterThanOrEqualTo(String value) {
            addCriterion("ordercode >=", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLessThan(String value) {
            addCriterion("ordercode <", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLessThanOrEqualTo(String value) {
            addCriterion("ordercode <=", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLike(String value) {
            addCriterion("ordercode like", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotLike(String value) {
            addCriterion("ordercode not like", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeIn(List<String> values) {
            addCriterion("ordercode in", values, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotIn(List<String> values) {
            addCriterion("ordercode not in", values, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeBetween(String value1, String value2) {
            addCriterion("ordercode between", value1, value2, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotBetween(String value1, String value2) {
            addCriterion("ordercode not between", value1, value2, "ordercode");
            return (Criteria) this;
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

        public Criteria andOrderfoodidIsNull() {
            addCriterion("orderfoodid is null");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidIsNotNull() {
            addCriterion("orderfoodid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidEqualTo(String value) {
            addCriterion("orderfoodid =", value, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidNotEqualTo(String value) {
            addCriterion("orderfoodid <>", value, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidGreaterThan(String value) {
            addCriterion("orderfoodid >", value, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidGreaterThanOrEqualTo(String value) {
            addCriterion("orderfoodid >=", value, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidLessThan(String value) {
            addCriterion("orderfoodid <", value, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidLessThanOrEqualTo(String value) {
            addCriterion("orderfoodid <=", value, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidLike(String value) {
            addCriterion("orderfoodid like", value, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidNotLike(String value) {
            addCriterion("orderfoodid not like", value, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidIn(List<String> values) {
            addCriterion("orderfoodid in", values, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidNotIn(List<String> values) {
            addCriterion("orderfoodid not in", values, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidBetween(String value1, String value2) {
            addCriterion("orderfoodid between", value1, value2, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodidNotBetween(String value1, String value2) {
            addCriterion("orderfoodid not between", value1, value2, "orderfoodid");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumIsNull() {
            addCriterion("orderfoodpricenum is null");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumIsNotNull() {
            addCriterion("orderfoodpricenum is not null");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumEqualTo(String value) {
            addCriterion("orderfoodpricenum =", value, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumNotEqualTo(String value) {
            addCriterion("orderfoodpricenum <>", value, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumGreaterThan(String value) {
            addCriterion("orderfoodpricenum >", value, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumGreaterThanOrEqualTo(String value) {
            addCriterion("orderfoodpricenum >=", value, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumLessThan(String value) {
            addCriterion("orderfoodpricenum <", value, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumLessThanOrEqualTo(String value) {
            addCriterion("orderfoodpricenum <=", value, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumLike(String value) {
            addCriterion("orderfoodpricenum like", value, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumNotLike(String value) {
            addCriterion("orderfoodpricenum not like", value, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumIn(List<String> values) {
            addCriterion("orderfoodpricenum in", values, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumNotIn(List<String> values) {
            addCriterion("orderfoodpricenum not in", values, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumBetween(String value1, String value2) {
            addCriterion("orderfoodpricenum between", value1, value2, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andOrderfoodpricenumNotBetween(String value1, String value2) {
            addCriterion("orderfoodpricenum not between", value1, value2, "orderfoodpricenum");
            return (Criteria) this;
        }

        public Criteria andUnoteIsNull() {
            addCriterion("uNote is null");
            return (Criteria) this;
        }

        public Criteria andUnoteIsNotNull() {
            addCriterion("uNote is not null");
            return (Criteria) this;
        }

        public Criteria andUnoteEqualTo(String value) {
            addCriterion("uNote =", value, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteNotEqualTo(String value) {
            addCriterion("uNote <>", value, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteGreaterThan(String value) {
            addCriterion("uNote >", value, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteGreaterThanOrEqualTo(String value) {
            addCriterion("uNote >=", value, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteLessThan(String value) {
            addCriterion("uNote <", value, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteLessThanOrEqualTo(String value) {
            addCriterion("uNote <=", value, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteLike(String value) {
            addCriterion("uNote like", value, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteNotLike(String value) {
            addCriterion("uNote not like", value, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteIn(List<String> values) {
            addCriterion("uNote in", values, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteNotIn(List<String> values) {
            addCriterion("uNote not in", values, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteBetween(String value1, String value2) {
            addCriterion("uNote between", value1, value2, "unote");
            return (Criteria) this;
        }

        public Criteria andUnoteNotBetween(String value1, String value2) {
            addCriterion("uNote not between", value1, value2, "unote");
            return (Criteria) this;
        }

        public Criteria andOrderdateIsNull() {
            addCriterion("orderdate is null");
            return (Criteria) this;
        }

        public Criteria andOrderdateIsNotNull() {
            addCriterion("orderdate is not null");
            return (Criteria) this;
        }

        public Criteria andOrderdateEqualTo(Date value) {
            addCriterion("orderdate =", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateNotEqualTo(Date value) {
            addCriterion("orderdate <>", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateGreaterThan(Date value) {
            addCriterion("orderdate >", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateGreaterThanOrEqualTo(Date value) {
            addCriterion("orderdate >=", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateLessThan(Date value) {
            addCriterion("orderdate <", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateLessThanOrEqualTo(Date value) {
            addCriterion("orderdate <=", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateIn(List<Date> values) {
            addCriterion("orderdate in", values, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateNotIn(List<Date> values) {
            addCriterion("orderdate not in", values, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateBetween(Date value1, Date value2) {
            addCriterion("orderdate between", value1, value2, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateNotBetween(Date value1, Date value2) {
            addCriterion("orderdate not between", value1, value2, "orderdate");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andCommentTwoIsNull() {
            addCriterion("comment_two is null");
            return (Criteria) this;
        }

        public Criteria andCommentTwoIsNotNull() {
            addCriterion("comment_two is not null");
            return (Criteria) this;
        }

        public Criteria andCommentTwoEqualTo(String value) {
            addCriterion("comment_two =", value, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoNotEqualTo(String value) {
            addCriterion("comment_two <>", value, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoGreaterThan(String value) {
            addCriterion("comment_two >", value, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoGreaterThanOrEqualTo(String value) {
            addCriterion("comment_two >=", value, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoLessThan(String value) {
            addCriterion("comment_two <", value, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoLessThanOrEqualTo(String value) {
            addCriterion("comment_two <=", value, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoLike(String value) {
            addCriterion("comment_two like", value, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoNotLike(String value) {
            addCriterion("comment_two not like", value, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoIn(List<String> values) {
            addCriterion("comment_two in", values, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoNotIn(List<String> values) {
            addCriterion("comment_two not in", values, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoBetween(String value1, String value2) {
            addCriterion("comment_two between", value1, value2, "commentTwo");
            return (Criteria) this;
        }

        public Criteria andCommentTwoNotBetween(String value1, String value2) {
            addCriterion("comment_two not between", value1, value2, "commentTwo");
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