package com.lbf.wsdc.pojo.po;

import java.util.ArrayList;
import java.util.List;

public class StoremenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StoremenuExample() {
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

        public Criteria andFoodidIsNull() {
            addCriterion("foodid is null");
            return (Criteria) this;
        }

        public Criteria andFoodidIsNotNull() {
            addCriterion("foodid is not null");
            return (Criteria) this;
        }

        public Criteria andFoodidEqualTo(Integer value) {
            addCriterion("foodid =", value, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodidNotEqualTo(Integer value) {
            addCriterion("foodid <>", value, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodidGreaterThan(Integer value) {
            addCriterion("foodid >", value, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodidGreaterThanOrEqualTo(Integer value) {
            addCriterion("foodid >=", value, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodidLessThan(Integer value) {
            addCriterion("foodid <", value, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodidLessThanOrEqualTo(Integer value) {
            addCriterion("foodid <=", value, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodidIn(List<Integer> values) {
            addCriterion("foodid in", values, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodidNotIn(List<Integer> values) {
            addCriterion("foodid not in", values, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodidBetween(Integer value1, Integer value2) {
            addCriterion("foodid between", value1, value2, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodidNotBetween(Integer value1, Integer value2) {
            addCriterion("foodid not between", value1, value2, "foodid");
            return (Criteria) this;
        }

        public Criteria andFoodnameIsNull() {
            addCriterion("foodname is null");
            return (Criteria) this;
        }

        public Criteria andFoodnameIsNotNull() {
            addCriterion("foodname is not null");
            return (Criteria) this;
        }

        public Criteria andFoodnameEqualTo(String value) {
            addCriterion("foodname =", value, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameNotEqualTo(String value) {
            addCriterion("foodname <>", value, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameGreaterThan(String value) {
            addCriterion("foodname >", value, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameGreaterThanOrEqualTo(String value) {
            addCriterion("foodname >=", value, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameLessThan(String value) {
            addCriterion("foodname <", value, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameLessThanOrEqualTo(String value) {
            addCriterion("foodname <=", value, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameLike(String value) {
            addCriterion("foodname like", value, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameNotLike(String value) {
            addCriterion("foodname not like", value, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameIn(List<String> values) {
            addCriterion("foodname in", values, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameNotIn(List<String> values) {
            addCriterion("foodname not in", values, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameBetween(String value1, String value2) {
            addCriterion("foodname between", value1, value2, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodnameNotBetween(String value1, String value2) {
            addCriterion("foodname not between", value1, value2, "foodname");
            return (Criteria) this;
        }

        public Criteria andFoodpricesIsNull() {
            addCriterion("foodprices is null");
            return (Criteria) this;
        }

        public Criteria andFoodpricesIsNotNull() {
            addCriterion("foodprices is not null");
            return (Criteria) this;
        }

        public Criteria andFoodpricesEqualTo(Float value) {
            addCriterion("foodprices =", value, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodpricesNotEqualTo(Float value) {
            addCriterion("foodprices <>", value, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodpricesGreaterThan(Float value) {
            addCriterion("foodprices >", value, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodpricesGreaterThanOrEqualTo(Float value) {
            addCriterion("foodprices >=", value, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodpricesLessThan(Float value) {
            addCriterion("foodprices <", value, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodpricesLessThanOrEqualTo(Float value) {
            addCriterion("foodprices <=", value, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodpricesIn(List<Float> values) {
            addCriterion("foodprices in", values, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodpricesNotIn(List<Float> values) {
            addCriterion("foodprices not in", values, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodpricesBetween(Float value1, Float value2) {
            addCriterion("foodprices between", value1, value2, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodpricesNotBetween(Float value1, Float value2) {
            addCriterion("foodprices not between", value1, value2, "foodprices");
            return (Criteria) this;
        }

        public Criteria andFoodtypeIsNull() {
            addCriterion("foodtype is null");
            return (Criteria) this;
        }

        public Criteria andFoodtypeIsNotNull() {
            addCriterion("foodtype is not null");
            return (Criteria) this;
        }

        public Criteria andFoodtypeEqualTo(String value) {
            addCriterion("foodtype =", value, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeNotEqualTo(String value) {
            addCriterion("foodtype <>", value, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeGreaterThan(String value) {
            addCriterion("foodtype >", value, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeGreaterThanOrEqualTo(String value) {
            addCriterion("foodtype >=", value, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeLessThan(String value) {
            addCriterion("foodtype <", value, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeLessThanOrEqualTo(String value) {
            addCriterion("foodtype <=", value, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeLike(String value) {
            addCriterion("foodtype like", value, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeNotLike(String value) {
            addCriterion("foodtype not like", value, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeIn(List<String> values) {
            addCriterion("foodtype in", values, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeNotIn(List<String> values) {
            addCriterion("foodtype not in", values, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeBetween(String value1, String value2) {
            addCriterion("foodtype between", value1, value2, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodtypeNotBetween(String value1, String value2) {
            addCriterion("foodtype not between", value1, value2, "foodtype");
            return (Criteria) this;
        }

        public Criteria andFoodicIsNull() {
            addCriterion("foodic is null");
            return (Criteria) this;
        }

        public Criteria andFoodicIsNotNull() {
            addCriterion("foodic is not null");
            return (Criteria) this;
        }

        public Criteria andFoodicEqualTo(String value) {
            addCriterion("foodic =", value, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicNotEqualTo(String value) {
            addCriterion("foodic <>", value, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicGreaterThan(String value) {
            addCriterion("foodic >", value, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicGreaterThanOrEqualTo(String value) {
            addCriterion("foodic >=", value, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicLessThan(String value) {
            addCriterion("foodic <", value, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicLessThanOrEqualTo(String value) {
            addCriterion("foodic <=", value, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicLike(String value) {
            addCriterion("foodic like", value, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicNotLike(String value) {
            addCriterion("foodic not like", value, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicIn(List<String> values) {
            addCriterion("foodic in", values, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicNotIn(List<String> values) {
            addCriterion("foodic not in", values, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicBetween(String value1, String value2) {
            addCriterion("foodic between", value1, value2, "foodic");
            return (Criteria) this;
        }

        public Criteria andFoodicNotBetween(String value1, String value2) {
            addCriterion("foodic not between", value1, value2, "foodic");
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