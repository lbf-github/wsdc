package com.lbf.wsdc.pojo.po;

import java.util.ArrayList;
import java.util.List;

public class StoreinfoTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StoreinfoTypeExample() {
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

        public Criteria andStoreinfotypeidIsNull() {
            addCriterion("storeinfotypeid is null");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidIsNotNull() {
            addCriterion("storeinfotypeid is not null");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidEqualTo(Long value) {
            addCriterion("storeinfotypeid =", value, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidNotEqualTo(Long value) {
            addCriterion("storeinfotypeid <>", value, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidGreaterThan(Long value) {
            addCriterion("storeinfotypeid >", value, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidGreaterThanOrEqualTo(Long value) {
            addCriterion("storeinfotypeid >=", value, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidLessThan(Long value) {
            addCriterion("storeinfotypeid <", value, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidLessThanOrEqualTo(Long value) {
            addCriterion("storeinfotypeid <=", value, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidIn(List<Long> values) {
            addCriterion("storeinfotypeid in", values, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidNotIn(List<Long> values) {
            addCriterion("storeinfotypeid not in", values, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidBetween(Long value1, Long value2) {
            addCriterion("storeinfotypeid between", value1, value2, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreinfotypeidNotBetween(Long value1, Long value2) {
            addCriterion("storeinfotypeid not between", value1, value2, "storeinfotypeid");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNull() {
            addCriterion("storeid is null");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNotNull() {
            addCriterion("storeid is not null");
            return (Criteria) this;
        }

        public Criteria andStoreidEqualTo(Long value) {
            addCriterion("storeid =", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotEqualTo(Long value) {
            addCriterion("storeid <>", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThan(Long value) {
            addCriterion("storeid >", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThanOrEqualTo(Long value) {
            addCriterion("storeid >=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThan(Long value) {
            addCriterion("storeid <", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThanOrEqualTo(Long value) {
            addCriterion("storeid <=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidIn(List<Long> values) {
            addCriterion("storeid in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotIn(List<Long> values) {
            addCriterion("storeid not in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidBetween(Long value1, Long value2) {
            addCriterion("storeid between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotBetween(Long value1, Long value2) {
            addCriterion("storeid not between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andStypeidIsNull() {
            addCriterion("stypeid is null");
            return (Criteria) this;
        }

        public Criteria andStypeidIsNotNull() {
            addCriterion("stypeid is not null");
            return (Criteria) this;
        }

        public Criteria andStypeidEqualTo(Integer value) {
            addCriterion("stypeid =", value, "stypeid");
            return (Criteria) this;
        }

        public Criteria andStypeidNotEqualTo(Integer value) {
            addCriterion("stypeid <>", value, "stypeid");
            return (Criteria) this;
        }

        public Criteria andStypeidGreaterThan(Integer value) {
            addCriterion("stypeid >", value, "stypeid");
            return (Criteria) this;
        }

        public Criteria andStypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("stypeid >=", value, "stypeid");
            return (Criteria) this;
        }

        public Criteria andStypeidLessThan(Integer value) {
            addCriterion("stypeid <", value, "stypeid");
            return (Criteria) this;
        }

        public Criteria andStypeidLessThanOrEqualTo(Integer value) {
            addCriterion("stypeid <=", value, "stypeid");
            return (Criteria) this;
        }

        public Criteria andStypeidIn(List<Integer> values) {
            addCriterion("stypeid in", values, "stypeid");
            return (Criteria) this;
        }

        public Criteria andStypeidNotIn(List<Integer> values) {
            addCriterion("stypeid not in", values, "stypeid");
            return (Criteria) this;
        }

        public Criteria andStypeidBetween(Integer value1, Integer value2) {
            addCriterion("stypeid between", value1, value2, "stypeid");
            return (Criteria) this;
        }

        public Criteria andStypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("stypeid not between", value1, value2, "stypeid");
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