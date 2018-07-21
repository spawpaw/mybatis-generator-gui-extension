package com.spawpaw.mybatis.generator.gui.entity;

import java.util.Arrays;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created By MBG-GUI-EXTENSION https://github.com/spawpaw/mybatis-generator-gui-extension
 * Description:
 *
 * @author
 * @mbg.generated
 */
@Table(name = "`t_test`")
public class TestEntity implements Serializable {

    /**
     * Corresponding to the database column t_test.field_not_having_default_value
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String field_not_having_default_value;

    /**
     * Corresponding to the database column t_test.name
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String name;

    /**
     * Corresponding to the database column t_test.field_int
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private Integer field_int;

    /**
     * Corresponding to the database column t_test.id
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private Integer id;

    /**
     * Corresponding to the database table t_test
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * Corresponding to the database column t_test.drop
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String drop;

    /**
     * Corresponding to the database column t_test.fieldLowerCamel
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String fieldLowerCamel;

    /**
     * Corresponding to the database column t_test._fieldTest
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String _fieldTest;

    /**
     * Corresponding to the database column t_test.group
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String group;

    /**
     * Corresponding to the database column t_test.select
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String select;

    /**
     * Corresponding to the database column t_test.field_having_default_value
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String field_having_default_value;

    /**
     * Corresponding to the database column t_test.field_test_
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String field_test_;

    /**
     * Corresponding to the database column t_test.FIELD_UPPER_UNDERSCORE
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String FIELD_UPPER_UNDERSCORE;

    /**
     * Corresponding to the database column t_test._field_test2
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String _field_test2;

    /**
     * Corresponding to the database column t_test.SINGLE
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String SINGLE;

    /**
     * Corresponding to the database column t_test.deleted
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private Boolean deleted;

    /**
     * Corresponding to the database column t_test.field_boolean
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private Boolean field_boolean;

    /**
     * Corresponding to the database column t_test.FieldUpperCamel
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private String fieldUpperCamel;

    /**
     * Corresponding to the database column t_test.field_double
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    private Double field_double;

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withField_boolean(Boolean field_boolean) {
        this.setField_boolean(field_boolean);
        return this;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withField_double(Double field_double) {
        this.setField_double(field_double);
        return this;
    }

    /**
     * This method sets the value of the database column t_test.FieldUpperCamel
     *
     * @param fieldUpperCamel the value for t_test.FieldUpperCamel
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setFieldUpperCamel(String fieldUpperCamel) {
        this.fieldUpperCamel = fieldUpperCamel;
    }

    /**
     * This method sets the value of the database column t_test.fieldLowerCamel
     *
     * @param fieldLowerCamel the value for t_test.fieldLowerCamel
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setFieldLowerCamel(String fieldLowerCamel) {
        this.fieldLowerCamel = fieldLowerCamel;
    }

    /**
     * This method sets the value of the database column t_test.field_not_having_default_value
     *
     * @param field_not_having_default_value the value for t_test.field_not_having_default_value
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setField_not_having_default_value(String field_not_having_default_value) {
        this.field_not_having_default_value = field_not_having_default_value;
    }

    /**
     * This method returns the value of the database column t_test.deleted
     *
     * @return the value of t_test.deleted
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method sets the value of the database column t_test.group
     *
     * @param group the value for t_test.group
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * This method returns the value of the database column t_test.field_boolean
     *
     * @return the value of t_test.field_boolean
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public Boolean getField_boolean() {
        return field_boolean;
    }

    /**
     * This method returns the value of the database column t_test.select
     *
     * @return the value of t_test.select
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getSelect() {
        return select;
    }

    /**
     * This method returns the value of the database column t_test.FIELD_UPPER_UNDERSCORE
     *
     * @return the value of t_test.FIELD_UPPER_UNDERSCORE
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getFIELD_UPPER_UNDERSCORE() {
        return FIELD_UPPER_UNDERSCORE;
    }

    /**
     * This method sets the value of the database column t_test.id
     *
     * @param id the value for t_test.id
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column t_test.id
     *
     * @return the value of t_test.id
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method sets the value of the database column t_test.name
     *
     * @param name the value for t_test.name
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withFieldUpperCamel(String fieldUpperCamel) {
        this.setFieldUpperCamel(fieldUpperCamel);
        return this;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withFieldLowerCamel(String fieldLowerCamel) {
        this.setFieldLowerCamel(fieldLowerCamel);
        return this;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withDeleted(Boolean deleted) {
        this.setDeleted(deleted);
        return this;
    }

    /**
     * This method sets the value of the database column t_test.field_boolean
     *
     * @param field_boolean the value for t_test.field_boolean
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setField_boolean(Boolean field_boolean) {
        this.field_boolean = field_boolean;
    }

    /**
     * This method sets the value of the database column t_test.drop
     *
     * @param drop the value for t_test.drop
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setDrop(String drop) {
        this.drop = drop;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withSelect(String select) {
        this.setSelect(select);
        return this;
    }

    /**
     * This method sets the value of the database column t_test.select
     *
     * @param select the value for t_test.select
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setSelect(String select) {
        this.select = select;
    }

    /**
     * This method sets the value of the database column t_test.field_int
     *
     * @param field_int the value for t_test.field_int
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setField_int(Integer field_int) {
        this.field_int = field_int;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withField_not_having_default_value(String field_not_having_default_value) {
        this.setField_not_having_default_value(field_not_having_default_value);
        return this;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withField_having_default_value(String field_having_default_value) {
        this.setField_having_default_value(field_having_default_value);
        return this;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withField_int(Integer field_int) {
        this.setField_int(field_int);
        return this;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getField_int() == null) ? 0 : getField_int().hashCode());
        result = prime * result + ((getField_boolean() == null) ? 0 : getField_boolean().hashCode());
        result = prime * result + ((getField_double() == null) ? 0 : getField_double().hashCode());
        result = prime * result + ((get_fieldTest() == null) ? 0 : get_fieldTest().hashCode());
        result = prime * result + ((get_field_test2() == null) ? 0 : get_field_test2().hashCode());
        result = prime * result + ((getField_test_() == null) ? 0 : getField_test_().hashCode());
        result = prime * result + ((getFieldLowerCamel() == null) ? 0 : getFieldLowerCamel().hashCode());
        result = prime * result + ((getFieldUpperCamel() == null) ? 0 : getFieldUpperCamel().hashCode());
        result = prime * result + ((getSINGLE() == null) ? 0 : getSINGLE().hashCode());
        result = prime * result + ((getFIELD_UPPER_UNDERSCORE() == null) ? 0 : getFIELD_UPPER_UNDERSCORE().hashCode());
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getSelect() == null) ? 0 : getSelect().hashCode());
        result = prime * result + ((getDrop() == null) ? 0 : getDrop().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getField_having_default_value() == null) ? 0 : getField_having_default_value().hashCode());
        result = prime * result + ((getField_not_having_default_value() == null) ? 0 : getField_not_having_default_value().hashCode());
        return result;
    }

    /**
     * This method sets the value of the database column t_test.field_double
     *
     * @param field_double the value for t_test.field_double
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setField_double(Double field_double) {
        this.field_double = field_double;
    }

    /**
     * This method returns the value of the database column t_test.field_having_default_value
     *
     * @return the value of t_test.field_having_default_value
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getField_having_default_value() {
        return field_having_default_value;
    }

    /**
     * This method returns the value of the database column t_test.drop
     *
     * @return the value of t_test.drop
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getDrop() {
        return drop;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withGroup(String group) {
        this.setGroup(group);
        return this;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * This method returns the value of the database column t_test.group
     *
     * @return the value of t_test.group
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getGroup() {
        return group;
    }

    /**
     * This method returns the value of the database column t_test._fieldTest
     *
     * @return the value of t_test._fieldTest
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String get_fieldTest() {
        return _fieldTest;
    }

    /**
     * This method returns the value of the database column t_test.field_int
     *
     * @return the value of t_test.field_int
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public Integer getField_int() {
        return field_int;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity with_field_test2(String _field_test2) {
        this.set_field_test2(_field_test2);
        return this;
    }

    /**
     * This method returns the value of the database column t_test.field_double
     *
     * @return the value of t_test.field_double
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public Double getField_double() {
        return field_double;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withField_test_(String field_test_) {
        this.setField_test_(field_test_);
        return this;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withDrop(String drop) {
        this.setDrop(drop);
        return this;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withSINGLE(String SINGLE) {
        this.setSINGLE(SINGLE);
        return this;
    }

    /**
     * This method sets the value of the database column t_test.field_having_default_value
     *
     * @param field_having_default_value the value for t_test.field_having_default_value
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setField_having_default_value(String field_having_default_value) {
        this.field_having_default_value = field_having_default_value;
    }

    /**
     * This method returns the value of the database column t_test.FieldUpperCamel
     *
     * @return the value of t_test.FieldUpperCamel
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getFieldUpperCamel() {
        return fieldUpperCamel;
    }

    /**
     * This method sets the value of the database column t_test.SINGLE
     *
     * @param SINGLE the value for t_test.SINGLE
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setSINGLE(String SINGLE) {
        this.SINGLE = SINGLE;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TestEntity other = (TestEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) && (this.getField_int() == null ? other.getField_int() == null : this.getField_int().equals(other.getField_int())) && (this.getField_boolean() == null ? other.getField_boolean() == null : this.getField_boolean().equals(other.getField_boolean())) && (this.getField_double() == null ? other.getField_double() == null : this.getField_double().equals(other.getField_double())) && (this.get_fieldTest() == null ? other.get_fieldTest() == null : this.get_fieldTest().equals(other.get_fieldTest())) && (this.get_field_test2() == null ? other.get_field_test2() == null : this.get_field_test2().equals(other.get_field_test2())) && (this.getField_test_() == null ? other.getField_test_() == null : this.getField_test_().equals(other.getField_test_())) && (this.getFieldLowerCamel() == null ? other.getFieldLowerCamel() == null : this.getFieldLowerCamel().equals(other.getFieldLowerCamel())) && (this.getFieldUpperCamel() == null ? other.getFieldUpperCamel() == null : this.getFieldUpperCamel().equals(other.getFieldUpperCamel())) && (this.getSINGLE() == null ? other.getSINGLE() == null : this.getSINGLE().equals(other.getSINGLE())) && (this.getFIELD_UPPER_UNDERSCORE() == null ? other.getFIELD_UPPER_UNDERSCORE() == null : this.getFIELD_UPPER_UNDERSCORE().equals(other.getFIELD_UPPER_UNDERSCORE())) && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup())) && (this.getSelect() == null ? other.getSelect() == null : this.getSelect().equals(other.getSelect())) && (this.getDrop() == null ? other.getDrop() == null : this.getDrop().equals(other.getDrop())) && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted())) && (this.getField_having_default_value() == null ? other.getField_having_default_value() == null : this.getField_having_default_value().equals(other.getField_having_default_value())) && (this.getField_not_having_default_value() == null ? other.getField_not_having_default_value() == null : this.getField_not_having_default_value().equals(other.getField_not_having_default_value()));
    }

    /**
     * This method returns the value of the database column t_test.field_test_
     *
     * @return the value of t_test.field_test_
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getField_test_() {
        return field_test_;
    }

    /**
     * This method returns the value of the database column t_test.name
     *
     * @return the value of t_test.name
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the value of the database column t_test._field_test2
     *
     * @return the value of t_test._field_test2
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String get_field_test2() {
        return _field_test2;
    }

    /**
     * This method returns the value of the database column t_test.field_not_having_default_value
     *
     * @return the value of t_test.field_not_having_default_value
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getField_not_having_default_value() {
        return field_not_having_default_value;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", field_int=").append(field_int);
        sb.append(", field_boolean=").append(field_boolean);
        sb.append(", field_double=").append(field_double);
        sb.append(", _fieldTest=").append(_fieldTest);
        sb.append(", _field_test2=").append(_field_test2);
        sb.append(", field_test_=").append(field_test_);
        sb.append(", fieldLowerCamel=").append(fieldLowerCamel);
        sb.append(", fieldUpperCamel=").append(fieldUpperCamel);
        sb.append(", SINGLE=").append(SINGLE);
        sb.append(", FIELD_UPPER_UNDERSCORE=").append(FIELD_UPPER_UNDERSCORE);
        sb.append(", group=").append(group);
        sb.append(", select=").append(select);
        sb.append(", drop=").append(drop);
        sb.append(", deleted=").append(deleted);
        sb.append(", field_having_default_value=").append(field_having_default_value);
        sb.append(", field_not_having_default_value=").append(field_not_having_default_value);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method sets the value of the database column t_test.deleted
     *
     * @param deleted the value for t_test.deleted
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method returns the value of the database column t_test.SINGLE
     *
     * @return the value of t_test.SINGLE
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getSINGLE() {
        return SINGLE;
    }

    /**
     * This method sets the value of the database column t_test.FIELD_UPPER_UNDERSCORE
     *
     * @param FIELD_UPPER_UNDERSCORE the value for t_test.FIELD_UPPER_UNDERSCORE
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setFIELD_UPPER_UNDERSCORE(String FIELD_UPPER_UNDERSCORE) {
        this.FIELD_UPPER_UNDERSCORE = FIELD_UPPER_UNDERSCORE;
    }

    /**
     * This method returns the value of the database column t_test.fieldLowerCamel
     *
     * @return the value of t_test.fieldLowerCamel
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public String getFieldLowerCamel() {
        return fieldLowerCamel;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withFIELD_UPPER_UNDERSCORE(String FIELD_UPPER_UNDERSCORE) {
        this.setFIELD_UPPER_UNDERSCORE(FIELD_UPPER_UNDERSCORE);
        return this;
    }

    /**
     * This method sets the value of the database column t_test._field_test2
     *
     * @param _field_test2 the value for t_test._field_test2
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void set_field_test2(String _field_test2) {
        this._field_test2 = _field_test2;
    }

    /**
     * This method sets the value of the database column t_test._fieldTest
     *
     * @param _fieldTest the value for t_test._fieldTest
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void set_fieldTest(String _fieldTest) {
        this._fieldTest = _fieldTest;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity with_fieldTest(String _fieldTest) {
        this.set_fieldTest(_fieldTest);
        return this;
    }

    /**
     * This method sets the value of the database column t_test.field_test_
     *
     * @param field_test_ the value for t_test.field_test_
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public void setField_test_(String field_test_) {
        this.field_test_ = field_test_;
    }

    /**
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public TestEntity withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * corresponding to the database table t_test
     *
     * @mbg.generated Sat Jul 21 16:47:00 CST 2018
     */
    public enum Column {

        id("id", "id", "INTEGER", false),
        name("name", "name", "VARCHAR", true),
        field_int("field_int", "field_int", "INTEGER", false),
        field_boolean("field_boolean", "field_boolean", "BIT", false),
        field_double("field_double", "field_double", "DOUBLE", false),
        _fieldTest("_fieldTest", "_fieldTest", "VARCHAR", false),
        _field_test2("_field_test2", "_field_test2", "VARCHAR", false),
        field_test_("field_test_", "field_test_", "VARCHAR", false),
        fieldLowerCamel("fieldLowerCamel", "fieldLowerCamel", "VARCHAR", false),
        fieldUpperCamel("FieldUpperCamel", "fieldUpperCamel", "VARCHAR", false),
        SINGLE("SINGLE", "SINGLE", "VARCHAR", false),
        FIELD_UPPER_UNDERSCORE("FIELD_UPPER_UNDERSCORE", "FIELD_UPPER_UNDERSCORE", "VARCHAR", false),
        group("group", "group", "VARCHAR", true),
        select("select", "select", "VARCHAR", true),
        drop("drop", "drop", "VARCHAR", true),
        deleted("deleted", "deleted", "BIT", false),
        field_having_default_value("field_having_default_value", "field_having_default_value", "VARCHAR", false),
        field_not_having_default_value("field_not_having_default_value", "field_not_having_default_value", "VARCHAR", false);

        /**
         * Corresponding to the database table t_test
         *
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * Corresponding to the database table t_test
         *
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * Corresponding to the database table t_test
         *
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        private final String column;

        /**
         * Corresponding to the database table t_test
         *
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        private final boolean isColumnNameDelimited;

        /**
         * Corresponding to the database table t_test
         *
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        private final String javaProperty;

        /**
         * Corresponding to the database table t_test
         *
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        private final String jdbcType;

        /**
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        public String value() {
            return this.column;
        }

        /**
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        public String getValue() {
            return this.column;
        }

        /**
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        public static Column[] excludes(Column... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[] {});
        }

        /**
         * @mbg.generated Sat Jul 21 16:47:00 CST 2018
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }
    }
}
