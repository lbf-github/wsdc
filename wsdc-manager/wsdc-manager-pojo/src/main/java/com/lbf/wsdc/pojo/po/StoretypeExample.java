package com.lbf.wsdc.pojo.po;

import java.util.ArrayList;
import java.util.List;

public class StoretypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StoretypeExample() {
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

        public Criteria andTypenameIsNull() {
            addCriterion("typename is null");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNotNull() {
            addCriterion("typename is not null");
            return (Criteria) this;
        }

        public Criteria andTypenameEqualTo(String value) {
            addCriterion("typename =", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotEqualTo(String value) {
            addCriterion("typename <>", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThan(String value) {
            addCriterion("typename >", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThanOrEqualTo(String value) {
            addCriterion("typename >=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThan(String value) {
            addCriterion("typename <", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThanOrEqualTo(String value) {
            addCriterion("typename <=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLike(String value) {
            addCriterion("typename like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotLike(String value) {
            addCriterion("typename not like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameIn(List<String> values) {
            addCriterion("typename in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotIn(List<String> values) {
            addCriterion("typename not in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameBetween(String value1, String value2) {
            addCriterion("typename between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotBetween(String value1, String value2) {
            addCriterion("typename not between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andSupertypeidIsNull() {
            addCriterion("supertypeid is null");
            return (Criteria) this;
        }

        public Criteria andSupertypeidIsNotNull() {
            addCriterion("supertypeid is not null");
            return (Criteria) this;
        }

        public Criteria andSupertypeidEqualTo(Integer value) {
            addCriterion("supertypeid =", value, "supertypeid");
            return (Criteria) this;
        }

        public Criteria andSupertypeidNotEqualTo(Integer value) {
            addCriterion("supertypeid <>", value, "supertypeid");
            return (Criteria) this;
        }

        public Criteria andSupertypeidGreaterThan(Integer value) {
            addCriterion("supertypeid >", value, "supertypeid");
            return (Criteria) this;
        }

        public Criteria andSupertypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("supertypeid >=", value, "supertypeid");
            return (Criteria) this;
        }

        public Criteria andSupertypeidLessThan(Integer value) {
            addCriterion("supertypeid <", value, "supertypeid");
            return (Criteria) this;
        }

        public Criteria andSupertypeidLessThanOrEqualTo(Integer value) {
            addCriterion("supertypeid <=", value, "supertypeid");
            return (Criteria) this;
        }

        public Criteria andSupertypeidIn(List<Integer> values) {
            addCriterion("supertypeid in", values, "supertypeid");
            return (Criteria) this;
        }

        public Criteria andSupertypeidNotIn(List<Integer> values) {
            addCriterion("supertypeid not in", values, "supertypeid");
            return (Criteria) this;
        }

        public Criteria andSupertypeidBetween(Integer value1, Integer value2) {
            addCriterion("supertypeid between", value1, value2, "supertypeid");
            return (Criteria) this;
        }

        public Criteria andSupertypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("supertypeid not between", value1, value2, "supertypeid");
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