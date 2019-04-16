package com.xz.entity;

import java.util.ArrayList;
import java.util.List;

public class BopSysParamsExample {
    /**
     * sys_params
     */
    protected String orderByClause;

    /**
     * sys_params
     */
    protected boolean distinct;

    /**
     * sys_params
     */
    protected List<Criteria> oredCriteria;

    public BopSysParamsExample() {
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

    /**
     * sys_params 2019-02-22
     */
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andParamCnNameIsNull() {
            addCriterion("param_cn_name is null");
            return (Criteria) this;
        }

        public Criteria andParamCnNameIsNotNull() {
            addCriterion("param_cn_name is not null");
            return (Criteria) this;
        }

        public Criteria andParamCnNameEqualTo(String value) {
            addCriterion("param_cn_name =", value, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameNotEqualTo(String value) {
            addCriterion("param_cn_name <>", value, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameGreaterThan(String value) {
            addCriterion("param_cn_name >", value, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameGreaterThanOrEqualTo(String value) {
            addCriterion("param_cn_name >=", value, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameLessThan(String value) {
            addCriterion("param_cn_name <", value, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameLessThanOrEqualTo(String value) {
            addCriterion("param_cn_name <=", value, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameLike(String value) {
            addCriterion("param_cn_name like", value, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameNotLike(String value) {
            addCriterion("param_cn_name not like", value, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameIn(List<String> values) {
            addCriterion("param_cn_name in", values, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameNotIn(List<String> values) {
            addCriterion("param_cn_name not in", values, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameBetween(String value1, String value2) {
            addCriterion("param_cn_name between", value1, value2, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamCnNameNotBetween(String value1, String value2) {
            addCriterion("param_cn_name not between", value1, value2, "paramCnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameIsNull() {
            addCriterion("param_en_name is null");
            return (Criteria) this;
        }

        public Criteria andParamEnNameIsNotNull() {
            addCriterion("param_en_name is not null");
            return (Criteria) this;
        }

        public Criteria andParamEnNameEqualTo(String value) {
            addCriterion("param_en_name =", value, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameNotEqualTo(String value) {
            addCriterion("param_en_name <>", value, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameGreaterThan(String value) {
            addCriterion("param_en_name >", value, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("param_en_name >=", value, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameLessThan(String value) {
            addCriterion("param_en_name <", value, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameLessThanOrEqualTo(String value) {
            addCriterion("param_en_name <=", value, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameLike(String value) {
            addCriterion("param_en_name like", value, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameNotLike(String value) {
            addCriterion("param_en_name not like", value, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameIn(List<String> values) {
            addCriterion("param_en_name in", values, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameNotIn(List<String> values) {
            addCriterion("param_en_name not in", values, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameBetween(String value1, String value2) {
            addCriterion("param_en_name between", value1, value2, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamEnNameNotBetween(String value1, String value2) {
            addCriterion("param_en_name not between", value1, value2, "paramEnName");
            return (Criteria) this;
        }

        public Criteria andParamValueIsNull() {
            addCriterion("param_value is null");
            return (Criteria) this;
        }

        public Criteria andParamValueIsNotNull() {
            addCriterion("param_value is not null");
            return (Criteria) this;
        }

        public Criteria andParamValueEqualTo(String value) {
            addCriterion("param_value =", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotEqualTo(String value) {
            addCriterion("param_value <>", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueGreaterThan(String value) {
            addCriterion("param_value >", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueGreaterThanOrEqualTo(String value) {
            addCriterion("param_value >=", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLessThan(String value) {
            addCriterion("param_value <", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLessThanOrEqualTo(String value) {
            addCriterion("param_value <=", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLike(String value) {
            addCriterion("param_value like", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotLike(String value) {
            addCriterion("param_value not like", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueIn(List<String> values) {
            addCriterion("param_value in", values, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotIn(List<String> values) {
            addCriterion("param_value not in", values, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueBetween(String value1, String value2) {
            addCriterion("param_value between", value1, value2, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotBetween(String value1, String value2) {
            addCriterion("param_value not between", value1, value2, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNull() {
            addCriterion("param_type is null");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNotNull() {
            addCriterion("param_type is not null");
            return (Criteria) this;
        }

        public Criteria andParamTypeEqualTo(String value) {
            addCriterion("param_type =", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotEqualTo(String value) {
            addCriterion("param_type <>", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThan(String value) {
            addCriterion("param_type >", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThanOrEqualTo(String value) {
            addCriterion("param_type >=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThan(String value) {
            addCriterion("param_type <", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThanOrEqualTo(String value) {
            addCriterion("param_type <=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLike(String value) {
            addCriterion("param_type like", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotLike(String value) {
            addCriterion("param_type not like", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeIn(List<String> values) {
            addCriterion("param_type in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotIn(List<String> values) {
            addCriterion("param_type not in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeBetween(String value1, String value2) {
            addCriterion("param_type between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotBetween(String value1, String value2) {
            addCriterion("param_type not between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andIsModifyIsNull() {
            addCriterion("is_modify is null");
            return (Criteria) this;
        }

        public Criteria andIsModifyIsNotNull() {
            addCriterion("is_modify is not null");
            return (Criteria) this;
        }

        public Criteria andIsModifyEqualTo(String value) {
            addCriterion("is_modify =", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyNotEqualTo(String value) {
            addCriterion("is_modify <>", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyGreaterThan(String value) {
            addCriterion("is_modify >", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyGreaterThanOrEqualTo(String value) {
            addCriterion("is_modify >=", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyLessThan(String value) {
            addCriterion("is_modify <", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyLessThanOrEqualTo(String value) {
            addCriterion("is_modify <=", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyLike(String value) {
            addCriterion("is_modify like", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyNotLike(String value) {
            addCriterion("is_modify not like", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyIn(List<String> values) {
            addCriterion("is_modify in", values, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyNotIn(List<String> values) {
            addCriterion("is_modify not in", values, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyBetween(String value1, String value2) {
            addCriterion("is_modify between", value1, value2, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyNotBetween(String value1, String value2) {
            addCriterion("is_modify not between", value1, value2, "isModify");
            return (Criteria) this;
        }
    }

    /**
     * sys_params
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * sys_params 2019-02-22
     */
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