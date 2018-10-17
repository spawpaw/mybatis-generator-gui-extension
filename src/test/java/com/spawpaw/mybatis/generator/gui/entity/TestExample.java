package com.spawpaw.mybatis.generator.gui.entity;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.Entity;
import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TestExample {

    /**
     * @mbg.generated Sat Jul 21 16:44:43 CST 2018
     */
    public TestExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    private Integer offset;

    /**
     * Corresponding to the database table t_test
     *
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    protected String orderByClause;

    /**
     * Corresponding to the database table t_test
     *
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    protected List<Criteria> oredCriteria;

    private Integer limit;

    /**
     * Corresponding to the database table t_test
     *
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    protected boolean distinct;

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public void setPageInfo(Integer currentPage, Integer pageSize) {
        if (pageSize < 1)
            throw new IllegalArgumentException("页大小不能小于1！");
        this.limit = pageSize;
        if (currentPage < 1)
            throw new IllegalArgumentException("页数不能小于1！");
        this.offset = (currentPage - 1) * pageSize;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public TestExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public TestExample orderBy(String... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * Corresponding to the database table t_test
     *
     * @mbg.generated do_not_delete_during_merge Wed Oct 17 20:45:24 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        /**
         * @mbg.generated Sat Jul 21 16:44:43 CST 2018
         */
        protected Criteria(TestExample example) {
            super();
            this.example = example;
        }

        /**
         * Corresponding to the database table t_test
         *
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        private TestExample example;

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public TestExample example() {
            return this.example;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        public interface ICriteriaAdd {

            /**
             * @mbg.generated Wed Oct 17 20:45:24 CST 2018
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * Corresponding to the database table t_test
     *
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    protected abstract static class GeneratedCriteria {

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        protected List<Criterion> criteria;

        public Criteria andField_tinyintIsNull() {
            addCriterion("field_tinyint is null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_not_having_default_valueGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_not_having_default_value >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andField_booleanIn(List<Boolean> values) {
            addCriterion("field_boolean in", values, "field_boolean");
            return (Criteria) this;
        }

        public Criteria andField_doubleIsNull() {
            addCriterion("field_double is null");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCOREIn(List<String> values) {
            addCriterion("FIELD_UPPER_UNDERSCORE in", values, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        public Criteria andField_booleanLessThanOrEqualTo(Boolean value) {
            addCriterion("field_boolean <=", value, "field_boolean");
            return (Criteria) this;
        }

        public Criteria andSelectEqualTo(String value) {
            addCriterion("`select` =", value, "select");
            return (Criteria) this;
        }

        public Criteria and_fieldTestGreaterThan(String value) {
            addCriterion("_fieldTest >", value, "_fieldTest");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSINGLELessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("SINGLE < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupBetween(String value1, String value2) {
            addCriterion("`group` between", value1, value2, "group");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_test_NotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_test_ <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_tinyint4GreaterThanOrEqualTo(Byte value) {
            addCriterion("field_tinyint4 >=", value, "field_tinyint4");
            return (Criteria) this;
        }

        public Criteria andField_test_LikeInsensitive(String value) {
            addCriterion("upper(field_test_) like", value.toUpperCase(), "field_test_");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valLike(String value) {
            addCriterion("field_not_having_default_val like", value, "field_not_having_default_val");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDropGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`drop` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldLowerCamelGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("fieldLowerCamel >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLikeInsensitive(String value) {
            addCriterion("upper(`group`) like", value.toUpperCase(), "group");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_fieldTestGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_fieldTest > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(`name`) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldUpperCamelEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FieldUpperCamel = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThan(String value) {
            addCriterion("`group` <", value, "group");
            return (Criteria) this;
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public Criteria andField_tinyintEqualTo(Boolean value) {
            addCriterion("field_tinyint =", value, "field_tinyint");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_not_having_default_valueEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_not_having_default_value = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_doubleBetween(Double value1, Double value2) {
            addCriterion("field_double between", value1, value2, "field_double");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueNotLike(String value) {
            addCriterion("field_not_having_default_value not like", value, "field_not_having_default_value");
            return (Criteria) this;
        }

        public Criteria andField_intBetween(Integer value1, Integer value2) {
            addCriterion("field_int between", value1, value2, "field_int");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueEqualTo(String value) {
            addCriterion("field_not_having_default_value =", value, "field_not_having_default_value");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSelectNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`select` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupNotLike(String value) {
            addCriterion("`group` not like", value, "group");
            return (Criteria) this;
        }

        public Criteria andSINGLELessThanOrEqualTo(String value) {
            addCriterion("SINGLE <=", value, "SINGLE");
            return (Criteria) this;
        }

        public Criteria andSINGLELessThan(String value) {
            addCriterion("SINGLE <", value, "SINGLE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDeletedGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("deleted >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCORENotIn(List<String> values) {
            addCriterion("FIELD_UPPER_UNDERSCORE not in", values, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        public Criteria andSINGLENotLike(String value) {
            addCriterion("SINGLE not like", value, "SINGLE");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andSINGLEEqualTo(String value) {
            addCriterion("SINGLE =", value, "SINGLE");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCORENotEqualTo(String value) {
            addCriterion("FIELD_UPPER_UNDERSCORE <>", value, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyintGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valNotLike(String value) {
            addCriterion("field_not_having_default_val not like", value, "field_not_having_default_val");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_field_test2LessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_field_test2 <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_booleanNotBetween(Boolean value1, Boolean value2) {
            addCriterion("field_boolean not between", value1, value2, "field_boolean");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueIsNull() {
            addCriterion("field_having_default_value is null");
            return (Criteria) this;
        }

        public Criteria andField_tinyintIn(List<Boolean> values) {
            addCriterion("field_tinyint in", values, "field_tinyint");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDeletedLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("deleted < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_doubleNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_double <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_intLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_int < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_booleanIsNull() {
            addCriterion("field_boolean is null");
            return (Criteria) this;
        }

        public Criteria andGroupIn(List<String> values) {
            addCriterion("`group` in", values, "group");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueBetween(String value1, String value2) {
            addCriterion("field_having_default_value between", value1, value2, "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andField_tinyint4LessThan(Byte value) {
            addCriterion("field_tinyint4 <", value, "field_tinyint4");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueLessThan(String value) {
            addCriterion("field_not_having_default_value <", value, "field_not_having_default_value");
            return (Criteria) this;
        }

        public Criteria andField_tinyintGreaterThan(Boolean value) {
            addCriterion("field_tinyint >", value, "field_tinyint");
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelNotEqualTo(String value) {
            addCriterion("fieldLowerCamel <>", value, "fieldLowerCamel");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andGroupEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`group` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueLikeInsensitive(String value) {
            addCriterion("upper(field_having_default_value) like", value.toUpperCase(), "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria and_field_test2IsNull() {
            addCriterion("_field_test2 is null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDropGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`drop` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_booleanLessThan(Boolean value) {
            addCriterion("field_boolean <", value, "field_boolean");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_field_test2NotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_field_test2 <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_test_NotEqualTo(String value) {
            addCriterion("field_test_ <>", value, "field_test_");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCORENotLike(String value) {
            addCriterion("FIELD_UPPER_UNDERSCORE not like", value, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        public Criteria and_fieldTestNotBetween(String value1, String value2) {
            addCriterion("_fieldTest not between", value1, value2, "_fieldTest");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCOREEqualTo(String value) {
            addCriterion("FIELD_UPPER_UNDERSCORE =", value, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valNotBetween(String value1, String value2) {
            addCriterion("field_not_having_default_val not between", value1, value2, "field_not_having_default_val");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCOREGreaterThan(String value) {
            addCriterion("FIELD_UPPER_UNDERSCORE >", value, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFIELD_UPPER_UNDERSCORENotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FIELD_UPPER_UNDERSCORE <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_test_GreaterThanOrEqualTo(String value) {
            addCriterion("field_test_ >=", value, "field_test_");
            return (Criteria) this;
        }

        public Criteria andField_booleanGreaterThanOrEqualTo(Boolean value) {
            addCriterion("field_boolean >=", value, "field_boolean");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andNameGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`name` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSINGLEEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("SINGLE = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSINGLELike(String value) {
            addCriterion("SINGLE like", value, "SINGLE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_having_default_valueEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_having_default_value = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelGreaterThanOrEqualTo(String value) {
            addCriterion("fieldLowerCamel >=", value, "fieldLowerCamel");
            return (Criteria) this;
        }

        public Criteria andField_test_NotIn(List<String> values) {
            addCriterion("field_test_ not in", values, "field_test_");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_not_having_default_valueGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_not_having_default_value > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDropNotEqualTo(String value) {
            addCriterion("`drop` <>", value, "drop");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueIn(List<String> values) {
            addCriterion("field_not_having_default_value in", values, "field_not_having_default_value");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueNotLike(String value) {
            addCriterion("field_having_default_value not like", value, "field_having_default_value");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_booleanLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_boolean < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_doubleGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_double > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyintGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueEqualTo(String value) {
            addCriterion("field_having_default_value =", value, "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria andDropNotBetween(String value1, String value2) {
            addCriterion("`drop` not between", value1, value2, "drop");
            return (Criteria) this;
        }

        public Criteria and_fieldTestIsNotNull() {
            addCriterion("_fieldTest is not null");
            return (Criteria) this;
        }

        public Criteria andField_doubleNotIn(List<Double> values) {
            addCriterion("field_double not in", values, "field_double");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDropNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`drop` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_tinyint4LessThanOrEqualTo(Byte value) {
            addCriterion("field_tinyint4 <=", value, "field_tinyint4");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andSelectLessThanOrEqualTo(String value) {
            addCriterion("`select` <=", value, "select");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andSelectNotEqualTo(String value) {
            addCriterion("`select` <>", value, "select");
            return (Criteria) this;
        }

        public Criteria andDropLessThan(String value) {
            addCriterion("`drop` <", value, "drop");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andField_tinyint4Between(Byte value1, Byte value2) {
            addCriterion("field_tinyint4 between", value1, value2, "field_tinyint4");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualTo(String value) {
            addCriterion("`group` >=", value, "group");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valIsNotNull() {
            addCriterion("field_not_having_default_val is not null");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSINGLELessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("SINGLE <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_field_test2LessThanOrEqualTo(String value) {
            addCriterion("_field_test2 <=", value, "_field_test2");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_test_EqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_test_ = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_test_GreaterThan(String value) {
            addCriterion("field_test_ >", value, "field_test_");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valNotEqualTo(String value) {
            addCriterion("field_not_having_default_val <>", value, "field_not_having_default_val");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valNotIn(List<String> values) {
            addCriterion("field_not_having_default_val not in", values, "field_not_having_default_val");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueBetween(String value1, String value2) {
            addCriterion("field_not_having_default_value between", value1, value2, "field_not_having_default_value");
            return (Criteria) this;
        }

        public Criteria andDropLessThanOrEqualTo(String value) {
            addCriterion("`drop` <=", value, "drop");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_field_test2GreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_field_test2 >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_booleanGreaterThan(Boolean value) {
            addCriterion("field_boolean >", value, "field_boolean");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueLikeInsensitive(String value) {
            addCriterion("upper(field_not_having_default_value) like", value.toUpperCase(), "field_not_having_default_value");
            return (Criteria) this;
        }

        public Criteria andSINGLELikeInsensitive(String value) {
            addCriterion("upper(SINGLE) like", value.toUpperCase(), "SINGLE");
            return (Criteria) this;
        }

        public Criteria andGroupIsNotNull() {
            addCriterion("`group` is not null");
            return (Criteria) this;
        }

        public Criteria andField_doubleNotBetween(Double value1, Double value2) {
            addCriterion("field_double not between", value1, value2, "field_double");
            return (Criteria) this;
        }

        public Criteria andSINGLENotIn(List<String> values) {
            addCriterion("SINGLE not in", values, "SINGLE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andNameNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`name` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyint4LessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint4 < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueNotEqualTo(String value) {
            addCriterion("field_not_having_default_value <>", value, "field_not_having_default_value");
            return (Criteria) this;
        }

        public Criteria andField_booleanBetween(Boolean value1, Boolean value2) {
            addCriterion("field_boolean between", value1, value2, "field_boolean");
            return (Criteria) this;
        }

        public Criteria andDropIn(List<String> values) {
            addCriterion("`drop` in", values, "drop");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelIsNotNull() {
            addCriterion("FieldUpperCamel is not null");
            return (Criteria) this;
        }

        public Criteria andDropLikeInsensitive(String value) {
            addCriterion("upper(`drop`) like", value.toUpperCase(), "drop");
            return (Criteria) this;
        }

        public Criteria andField_tinyint4In(List<Byte> values) {
            addCriterion("field_tinyint4 in", values, "field_tinyint4");
            return (Criteria) this;
        }

        public Criteria andField_tinyint4NotEqualTo(Byte value) {
            addCriterion("field_tinyint4 <>", value, "field_tinyint4");
            return (Criteria) this;
        }

        public Criteria andField_intIsNull() {
            addCriterion("field_int is null");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCOREIsNotNull() {
            addCriterion("FIELD_UPPER_UNDERSCORE is not null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_having_default_valueLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_having_default_value <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldLowerCamelEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("fieldLowerCamel = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_field_test2LessThan(String value) {
            addCriterion("_field_test2 <", value, "_field_test2");
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelLessThanOrEqualTo(String value) {
            addCriterion("fieldLowerCamel <=", value, "fieldLowerCamel");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andField_test_Between(String value1, String value2) {
            addCriterion("field_test_ between", value1, value2, "field_test_");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valGreaterThan(String value) {
            addCriterion("field_not_having_default_val >", value, "field_not_having_default_val");
            return (Criteria) this;
        }

        public Criteria andSelectBetween(String value1, String value2) {
            addCriterion("`select` between", value1, value2, "select");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andNameLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`name` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelIsNull() {
            addCriterion("FieldUpperCamel is null");
            return (Criteria) this;
        }

        public Criteria andField_doubleNotEqualTo(Double value) {
            addCriterion("field_double <>", value, "field_double");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valIsNull() {
            addCriterion("field_not_having_default_val is null");
            return (Criteria) this;
        }

        public Criteria andSelectLikeInsensitive(String value) {
            addCriterion("upper(`select`) like", value.toUpperCase(), "select");
            return (Criteria) this;
        }

        public Criteria andField_intNotEqualTo(Integer value) {
            addCriterion("field_int <>", value, "field_int");
            return (Criteria) this;
        }

        public Criteria andField_tinyint4NotIn(List<Byte> values) {
            addCriterion("field_tinyint4 not in", values, "field_tinyint4");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyintNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyint4LessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint4 <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_doubleIsNotNull() {
            addCriterion("field_double is not null");
            return (Criteria) this;
        }

        public Criteria andSINGLEGreaterThan(String value) {
            addCriterion("SINGLE >", value, "SINGLE");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueLessThanOrEqualTo(String value) {
            addCriterion("field_having_default_value <=", value, "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelBetween(String value1, String value2) {
            addCriterion("fieldLowerCamel between", value1, value2, "fieldLowerCamel");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDropLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`drop` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSINGLENotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("SINGLE <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_doubleGreaterThan(Double value) {
            addCriterion("field_double >", value, "field_double");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria and_fieldTestBetween(String value1, String value2) {
            addCriterion("_fieldTest between", value1, value2, "_fieldTest");
            return (Criteria) this;
        }

        public Criteria and_field_test2In(List<String> values) {
            addCriterion("_field_test2 in", values, "_field_test2");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andNameLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`name` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldLowerCamelLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("fieldLowerCamel <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelLessThan(String value) {
            addCriterion("FieldUpperCamel <", value, "fieldUpperCamel");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSelectGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`select` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelLikeInsensitive(String value) {
            addCriterion("upper(fieldLowerCamel) like", value.toUpperCase(), "fieldLowerCamel");
            return (Criteria) this;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        public Criteria and_fieldTestLikeInsensitive(String value) {
            addCriterion("upper(_fieldTest) like", value.toUpperCase(), "_fieldTest");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelNotLike(String value) {
            addCriterion("FieldUpperCamel not like", value, "fieldUpperCamel");
            return (Criteria) this;
        }

        public Criteria andGroupEqualTo(String value) {
            addCriterion("`group` =", value, "group");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFIELD_UPPER_UNDERSCOREGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FIELD_UPPER_UNDERSCORE > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_doubleLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_double <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_test_IsNotNull() {
            addCriterion("field_test_ is not null");
            return (Criteria) this;
        }

        public Criteria andField_test_NotLike(String value) {
            addCriterion("field_test_ not like", value, "field_test_");
            return (Criteria) this;
        }

        public Criteria andField_intLessThan(Integer value) {
            addCriterion("field_int <", value, "field_int");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valEqualTo(String value) {
            addCriterion("field_not_having_default_val =", value, "field_not_having_default_val");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_having_default_valueNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_having_default_value <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDropNotLike(String value) {
            addCriterion("`drop` not like", value, "drop");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_field_test2GreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_field_test2 > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSINGLEGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("SINGLE >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelIn(List<String> values) {
            addCriterion("FieldUpperCamel in", values, "fieldUpperCamel");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_booleanLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_boolean <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelLike(String value) {
            addCriterion("FieldUpperCamel like", value, "fieldUpperCamel");
            return (Criteria) this;
        }

        public Criteria andSINGLEIsNull() {
            addCriterion("SINGLE is null");
            return (Criteria) this;
        }

        public Criteria andGroupNotBetween(String value1, String value2) {
            addCriterion("`group` not between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueGreaterThanOrEqualTo(String value) {
            addCriterion("field_having_default_value >=", value, "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria andDropLike(String value) {
            addCriterion("`drop` like", value, "drop");
            return (Criteria) this;
        }

        public Criteria andDropIsNull() {
            addCriterion("`drop` is null");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueLike(String value) {
            addCriterion("field_having_default_value like", value, "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueLessThan(String value) {
            addCriterion("field_having_default_value <", value, "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCOREBetween(String value1, String value2) {
            addCriterion("FIELD_UPPER_UNDERSCORE between", value1, value2, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        public Criteria andSelectLike(String value) {
            addCriterion("`select` like", value, "select");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andIdLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_tinyintIsNotNull() {
            addCriterion("field_tinyint is not null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_test_LessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_test_ < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCORENotBetween(String value1, String value2) {
            addCriterion("FIELD_UPPER_UNDERSCORE not between", value1, value2, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCOREGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_UPPER_UNDERSCORE >=", value, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        public Criteria andField_booleanNotIn(List<Boolean> values) {
            addCriterion("field_boolean not in", values, "field_boolean");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCORELikeInsensitive(String value) {
            addCriterion("upper(FIELD_UPPER_UNDERSCORE) like", value.toUpperCase(), "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andGroupGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`group` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_field_test2NotIn(List<String> values) {
            addCriterion("_field_test2 not in", values, "_field_test2");
            return (Criteria) this;
        }

        public Criteria andField_tinyintNotEqualTo(Boolean value) {
            addCriterion("field_tinyint <>", value, "field_tinyint");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSelectEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`select` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_field_test2NotLike(String value) {
            addCriterion("_field_test2 not like", value, "_field_test2");
            return (Criteria) this;
        }

        public Criteria andField_tinyint4NotBetween(Byte value1, Byte value2) {
            addCriterion("field_tinyint4 not between", value1, value2, "field_tinyint4");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueIn(List<String> values) {
            addCriterion("field_having_default_value in", values, "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria and_field_test2EqualTo(String value) {
            addCriterion("_field_test2 =", value, "_field_test2");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueNotBetween(String value1, String value2) {
            addCriterion("field_having_default_value not between", value1, value2, "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria and_fieldTestLessThanOrEqualTo(String value) {
            addCriterion("_fieldTest <=", value, "_fieldTest");
            return (Criteria) this;
        }

        public Criteria andField_tinyintGreaterThanOrEqualTo(Boolean value) {
            addCriterion("field_tinyint >=", value, "field_tinyint");
            return (Criteria) this;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public Criteria andGroupIsNull() {
            addCriterion("`group` is null");
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelNotLike(String value) {
            addCriterion("fieldLowerCamel not like", value, "fieldLowerCamel");
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelEqualTo(String value) {
            addCriterion("fieldLowerCamel =", value, "fieldLowerCamel");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueNotEqualTo(String value) {
            addCriterion("field_having_default_value <>", value, "field_having_default_value");
            return (Criteria) this;
        }

        public Criteria andSINGLENotEqualTo(String value) {
            addCriterion("SINGLE <>", value, "SINGLE");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelLikeInsensitive(String value) {
            addCriterion("upper(FieldUpperCamel) like", value.toUpperCase(), "fieldUpperCamel");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyint4EqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint4 = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueNotIn(List<String> values) {
            addCriterion("field_having_default_value not in", values, "field_having_default_value");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSelectLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`select` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_fieldTestLike(String value) {
            addCriterion("_fieldTest like", value, "_fieldTest");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFIELD_UPPER_UNDERSCOREEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FIELD_UPPER_UNDERSCORE = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldLowerCamelLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("fieldLowerCamel < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_field_test2EqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_field_test2 = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSINGLEBetween(String value1, String value2) {
            addCriterion("SINGLE between", value1, value2, "SINGLE");
            return (Criteria) this;
        }

        public Criteria and_field_test2GreaterThan(String value) {
            addCriterion("_field_test2 >", value, "_field_test2");
            return (Criteria) this;
        }

        public Criteria andField_intIsNotNull() {
            addCriterion("field_int is not null");
            return (Criteria) this;
        }

        public Criteria andField_tinyintNotBetween(Boolean value1, Boolean value2) {
            addCriterion("field_tinyint not between", value1, value2, "field_tinyint");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria and_fieldTestNotLike(String value) {
            addCriterion("_fieldTest not like", value, "_fieldTest");
            return (Criteria) this;
        }

        public Criteria andDropBetween(String value1, String value2) {
            addCriterion("`drop` between", value1, value2, "drop");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelNotIn(List<String> values) {
            addCriterion("FieldUpperCamel not in", values, "fieldUpperCamel");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_booleanGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_boolean > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelLike(String value) {
            addCriterion("fieldLowerCamel like", value, "fieldLowerCamel");
            return (Criteria) this;
        }

        public Criteria and_fieldTestNotEqualTo(String value) {
            addCriterion("_fieldTest <>", value, "_fieldTest");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyintEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyint4GreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint4 > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueGreaterThan(String value) {
            addCriterion("field_having_default_value >", value, "field_having_default_value");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSelectGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`select` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLike(String value) {
            addCriterion("`group` like", value, "group");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueNotIn(List<String> values) {
            addCriterion("field_not_having_default_value not in", values, "field_not_having_default_value");
            return (Criteria) this;
        }

        public Criteria andField_intGreaterThan(Integer value) {
            addCriterion("field_int >", value, "field_int");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_intLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_int <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSelectIsNull() {
            addCriterion("`select` is null");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCOREIsNull() {
            addCriterion("FIELD_UPPER_UNDERSCORE is null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDeletedLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("deleted <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_not_having_default_valueLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_not_having_default_value < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldLowerCamelGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("fieldLowerCamel > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_fieldTestIsNull() {
            addCriterion("_fieldTest is null");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelGreaterThanOrEqualTo(String value) {
            addCriterion("FieldUpperCamel >=", value, "fieldUpperCamel");
            return (Criteria) this;
        }

        public Criteria andField_intNotBetween(Integer value1, Integer value2) {
            addCriterion("field_int not between", value1, value2, "field_int");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelGreaterThan(String value) {
            addCriterion("FieldUpperCamel >", value, "fieldUpperCamel");
            return (Criteria) this;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_field_test2LessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_field_test2 < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_test_LessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_test_ <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_doubleGreaterThanOrEqualTo(Double value) {
            addCriterion("field_double >=", value, "field_double");
            return (Criteria) this;
        }

        public Criteria andField_tinyintNotIn(List<Boolean> values) {
            addCriterion("field_tinyint not in", values, "field_tinyint");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueGreaterThan(String value) {
            addCriterion("field_not_having_default_value >", value, "field_not_having_default_value");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDeletedEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("deleted = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueNotBetween(String value1, String value2) {
            addCriterion("field_not_having_default_value not between", value1, value2, "field_not_having_default_value");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_doubleLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_double < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_booleanGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_boolean >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andGroupLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`group` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelLessThanOrEqualTo(String value) {
            addCriterion("FieldUpperCamel <=", value, "fieldUpperCamel");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelEqualTo(String value) {
            addCriterion("FieldUpperCamel =", value, "fieldUpperCamel");
            return (Criteria) this;
        }

        public Criteria and_field_test2LikeInsensitive(String value) {
            addCriterion("upper(_field_test2) like", value.toUpperCase(), "_field_test2");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyintLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andIdNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_fieldTestEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_fieldTest = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_booleanNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_boolean <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldUpperCamelLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FieldUpperCamel < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_test_GreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_test_ > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valBetween(String value1, String value2) {
            addCriterion("field_not_having_default_val between", value1, value2, "field_not_having_default_val");
            return (Criteria) this;
        }

        public Criteria andField_test_EqualTo(String value) {
            addCriterion("field_test_ =", value, "field_test_");
            return (Criteria) this;
        }

        public Criteria andDropEqualTo(String value) {
            addCriterion("`drop` =", value, "drop");
            return (Criteria) this;
        }

        public Criteria andSelectNotLike(String value) {
            addCriterion("`select` not like", value, "select");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andIdEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_tinyint4IsNotNull() {
            addCriterion("field_tinyint4 is not null");
            return (Criteria) this;
        }

        public Criteria andField_booleanNotEqualTo(Boolean value) {
            addCriterion("field_boolean <>", value, "field_boolean");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valLikeInsensitive(String value) {
            addCriterion("upper(field_not_having_default_val) like", value.toUpperCase(), "field_not_having_default_val");
            return (Criteria) this;
        }

        public Criteria andSelectIsNotNull() {
            addCriterion("`select` is not null");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andSINGLEGreaterThanOrEqualTo(String value) {
            addCriterion("SINGLE >=", value, "SINGLE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyintLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_doubleLessThanOrEqualTo(Double value) {
            addCriterion("field_double <=", value, "field_double");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSelectLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`select` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valLessThanOrEqualTo(String value) {
            addCriterion("field_not_having_default_val <=", value, "field_not_having_default_val");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldLowerCamelNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("fieldLowerCamel <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_test_IsNull() {
            addCriterion("field_test_ is null");
            return (Criteria) this;
        }

        public Criteria andField_having_default_valueIsNotNull() {
            addCriterion("field_having_default_value is not null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldUpperCamelLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FieldUpperCamel <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDropEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`drop` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_booleanEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_boolean = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_having_default_valueGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_having_default_value >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valLessThan(String value) {
            addCriterion("field_not_having_default_val <", value, "field_not_having_default_val");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelNotIn(List<String> values) {
            addCriterion("fieldLowerCamel not in", values, "fieldLowerCamel");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_intGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_int > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDropGreaterThanOrEqualTo(String value) {
            addCriterion("`drop` >=", value, "drop");
            return (Criteria) this;
        }

        public Criteria and_field_test2NotEqualTo(String value) {
            addCriterion("_field_test2 <>", value, "_field_test2");
            return (Criteria) this;
        }

        public Criteria andField_tinyint4GreaterThan(Byte value) {
            addCriterion("field_tinyint4 >", value, "field_tinyint4");
            return (Criteria) this;
        }

        public Criteria andSelectNotIn(List<String> values) {
            addCriterion("`select` not in", values, "select");
            return (Criteria) this;
        }

        public Criteria andDropIsNotNull() {
            addCriterion("`drop` is not null");
            return (Criteria) this;
        }

        public Criteria andField_tinyint4EqualTo(Byte value) {
            addCriterion("field_tinyint4 =", value, "field_tinyint4");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andNameGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`name` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCORELessThanOrEqualTo(String value) {
            addCriterion("FIELD_UPPER_UNDERSCORE <=", value, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_not_having_default_valueNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_not_having_default_value <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andIdGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelBetween(String value1, String value2) {
            addCriterion("FieldUpperCamel between", value1, value2, "fieldUpperCamel");
            return (Criteria) this;
        }

        public Criteria andField_test_LessThan(String value) {
            addCriterion("field_test_ <", value, "field_test_");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valIn(List<String> values) {
            addCriterion("field_not_having_default_val in", values, "field_not_having_default_val");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andIdGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valGreaterThanOrEqualTo(String value) {
            addCriterion("field_not_having_default_val >=", value, "field_not_having_default_val");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelNotBetween(String value1, String value2) {
            addCriterion("FieldUpperCamel not between", value1, value2, "fieldUpperCamel");
            return (Criteria) this;
        }

        public Criteria andField_intNotIn(List<Integer> values) {
            addCriterion("field_int not in", values, "field_int");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andSINGLEGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("SINGLE > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelGreaterThan(String value) {
            addCriterion("fieldLowerCamel >", value, "fieldLowerCamel");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueGreaterThanOrEqualTo(String value) {
            addCriterion("field_not_having_default_value >=", value, "field_not_having_default_value");
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualTo(String value) {
            addCriterion("`group` <>", value, "group");
            return (Criteria) this;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFIELD_UPPER_UNDERSCOREGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FIELD_UPPER_UNDERSCORE >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andField_booleanEqualTo(Boolean value) {
            addCriterion("field_boolean =", value, "field_boolean");
            return (Criteria) this;
        }

        public Criteria and_field_test2NotBetween(String value1, String value2) {
            addCriterion("_field_test2 not between", value1, value2, "_field_test2");
            return (Criteria) this;
        }

        public Criteria andSelectGreaterThan(String value) {
            addCriterion("`select` >", value, "select");
            return (Criteria) this;
        }

        public Criteria andDropNotIn(List<String> values) {
            addCriterion("`drop` not in", values, "drop");
            return (Criteria) this;
        }

        public Criteria andField_test_NotBetween(String value1, String value2) {
            addCriterion("field_test_ not between", value1, value2, "field_test_");
            return (Criteria) this;
        }

        public Criteria andField_doubleIn(List<Double> values) {
            addCriterion("field_double in", values, "field_double");
            return (Criteria) this;
        }

        public Criteria andSINGLEIn(List<String> values) {
            addCriterion("SINGLE in", values, "SINGLE");
            return (Criteria) this;
        }

        public Criteria andSelectGreaterThanOrEqualTo(String value) {
            addCriterion("`select` >=", value, "select");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_doubleGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_double >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andIdLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andGroupGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`group` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSINGLENotBetween(String value1, String value2) {
            addCriterion("SINGLE not between", value1, value2, "SINGLE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_test_GreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_test_ >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andGroupNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`group` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_intNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_int <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSelectLessThan(String value) {
            addCriterion("`select` <", value, "select");
            return (Criteria) this;
        }

        public Criteria and_field_test2Like(String value) {
            addCriterion("_field_test2 like", value, "_field_test2");
            return (Criteria) this;
        }

        public Criteria andField_test_In(List<String> values) {
            addCriterion("field_test_ in", values, "field_test_");
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelLessThan(String value) {
            addCriterion("fieldLowerCamel <", value, "fieldLowerCamel");
            return (Criteria) this;
        }

        public Criteria and_fieldTestEqualTo(String value) {
            addCriterion("_fieldTest =", value, "_fieldTest");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueIsNull() {
            addCriterion("field_not_having_default_value is null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_fieldTestLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_fieldTest <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualTo(String value) {
            addCriterion("`group` <=", value, "group");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFIELD_UPPER_UNDERSCORELessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FIELD_UPPER_UNDERSCORE < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueLessThanOrEqualTo(String value) {
            addCriterion("field_not_having_default_value <=", value, "field_not_having_default_value");
            return (Criteria) this;
        }

        public Criteria andField_doubleLessThan(Double value) {
            addCriterion("field_double <", value, "field_double");
            return (Criteria) this;
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria and_fieldTestLessThan(String value) {
            addCriterion("_fieldTest <", value, "_fieldTest");
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueIsNotNull() {
            addCriterion("field_not_having_default_value is not null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_intEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_int = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDropGreaterThan(String value) {
            addCriterion("`drop` >", value, "drop");
            return (Criteria) this;
        }

        public Criteria andField_tinyintLessThanOrEqualTo(Boolean value) {
            addCriterion("field_tinyint <=", value, "field_tinyint");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldUpperCamelGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FieldUpperCamel > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFIELD_UPPER_UNDERSCORELessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FIELD_UPPER_UNDERSCORE <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_field_test2IsNotNull() {
            addCriterion("_field_test2 is not null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_fieldTestGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_fieldTest >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_intGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_int >=", value, "field_int");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyint4GreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint4 >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_tinyint4IsNull() {
            addCriterion("field_tinyint4 is null");
            return (Criteria) this;
        }

        public Criteria andGroupNotIn(List<String> values) {
            addCriterion("`group` not in", values, "group");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_fieldTestNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_fieldTest <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_not_having_default_valueLike(String value) {
            addCriterion("field_not_having_default_value like", value, "field_not_having_default_value");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_tinyint4NotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_tinyint4 <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_tinyintBetween(Boolean value1, Boolean value2) {
            addCriterion("field_tinyint between", value1, value2, "field_tinyint");
            return (Criteria) this;
        }

        public Criteria andSelectIn(List<String> values) {
            addCriterion("`select` in", values, "select");
            return (Criteria) this;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_having_default_valueLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_having_default_value < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelIn(List<String> values) {
            addCriterion("fieldLowerCamel in", values, "fieldLowerCamel");
            return (Criteria) this;
        }

        public Criteria andSelectNotBetween(String value1, String value2) {
            addCriterion("`select` not between", value1, value2, "select");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_intGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_int >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_fieldTestGreaterThanOrEqualTo(String value) {
            addCriterion("_fieldTest >=", value, "_fieldTest");
            return (Criteria) this;
        }

        public Criteria andField_test_Like(String value) {
            addCriterion("field_test_ like", value, "field_test_");
            return (Criteria) this;
        }

        public Criteria andFieldUpperCamelNotEqualTo(String value) {
            addCriterion("FieldUpperCamel <>", value, "fieldUpperCamel");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_doubleEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_double = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDeletedGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("deleted > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDropLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`drop` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCORELessThan(String value) {
            addCriterion("FIELD_UPPER_UNDERSCORE <", value, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        public Criteria andField_tinyintLessThan(Boolean value) {
            addCriterion("field_tinyint <", value, "field_tinyint");
            return (Criteria) this;
        }

        public Criteria andField_intEqualTo(Integer value) {
            addCriterion("field_int =", value, "field_int");
            return (Criteria) this;
        }

        public Criteria andField_intIn(List<Integer> values) {
            addCriterion("field_int in", values, "field_int");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_having_default_valueGreaterThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_having_default_value > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_fieldTestIn(List<String> values) {
            addCriterion("_fieldTest in", values, "_fieldTest");
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelIsNull() {
            addCriterion("fieldLowerCamel is null");
            return (Criteria) this;
        }

        public Criteria andFIELD_UPPER_UNDERSCORELike(String value) {
            addCriterion("FIELD_UPPER_UNDERSCORE like", value, "FIELD_UPPER_UNDERSCORE");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldUpperCamelNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FieldUpperCamel <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelNotBetween(String value1, String value2) {
            addCriterion("fieldLowerCamel not between", value1, value2, "fieldLowerCamel");
            return (Criteria) this;
        }

        public Criteria and_fieldTestNotIn(List<String> values) {
            addCriterion("_fieldTest not in", values, "_fieldTest");
            return (Criteria) this;
        }

        public Criteria andField_test_LessThanOrEqualTo(String value) {
            addCriterion("field_test_ <=", value, "field_test_");
            return (Criteria) this;
        }

        public Criteria andField_intLessThanOrEqualTo(Integer value) {
            addCriterion("field_int <=", value, "field_int");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andField_not_having_default_valueLessThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("field_not_having_default_value <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andDeletedNotEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("deleted <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFieldLowerCamelIsNotNull() {
            addCriterion("fieldLowerCamel is not null");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andGroupLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`group` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_field_test2Between(String value1, String value2) {
            addCriterion("_field_test2 between", value1, value2, "_field_test2");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria and_fieldTestLessThanColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("_fieldTest < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andFieldUpperCamelGreaterThanOrEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("FieldUpperCamel >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria and_field_test2GreaterThanOrEqualTo(String value) {
            addCriterion("_field_test2 >=", value, "_field_test2");
            return (Criteria) this;
        }

        public Criteria andField_booleanIsNotNull() {
            addCriterion("field_boolean is not null");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThan(String value) {
            addCriterion("`group` >", value, "group");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        /**
         * @mbg.generated Wed Oct 17 20:45:24 CST 2018
         */
        public Criteria andNameEqualToColumn(TestEntity.Column column) {
            addCriterion(new StringBuilder("`name` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andField_doubleEqualTo(Double value) {
            addCriterion("field_double =", value, "field_double");
            return (Criteria) this;
        }

        public Criteria andSINGLEIsNotNull() {
            addCriterion("SINGLE is not null");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }
    }

    /**
     * Corresponding to the database table t_test
     *
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    public static class Criterion {

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
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

        private String condition;

        private boolean singleValue;

        private boolean noValue;

        private Object secondValue;

        private Object value;

        private String typeHandler;

        private boolean betweenValue;

        private boolean listValue;

        public boolean isSingleValue() {
            return singleValue;
        }

        public String getCondition() {
            return condition;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        public boolean isListValue() {
            return listValue;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public Object getValue() {
            return value;
        }
    }
}
