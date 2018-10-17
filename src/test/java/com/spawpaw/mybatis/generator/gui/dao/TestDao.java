package com.spawpaw.mybatis.generator.gui.dao;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Table;
import com.spawpaw.mybatis.generator.gui.entity.TestEntity;
import java.util.List;
import javax.persistence.Entity;
import org.apache.ibatis.annotations.Param;
import com.spawpaw.mybatis.generator.gui.entity.TestExample;
import javax.persistence.GeneratedValue;
import org.apache.ibatis.annotations.Mapper;
import javax.persistence.Id;

@Mapper
public interface TestDao {

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    int updateByPrimaryKey(TestEntity record);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    int updateByExampleSelective(@Param("record") TestEntity record, @Param("example") TestExample example);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    TestEntity selectByPrimaryKey(Integer id);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    int updateByExample(@Param("record") TestEntity record, @Param("example") TestExample example);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    long countByExample(TestExample example);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    int updateByPrimaryKeySelective(TestEntity record);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    int deleteByExample(TestExample example);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    int insertSelective(TestEntity record);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    int insert(TestEntity record);

    /**
     * @mbg.generated Wed Oct 17 20:45:24 CST 2018
     */
    List<TestEntity> selectByExample(TestExample example);
}
