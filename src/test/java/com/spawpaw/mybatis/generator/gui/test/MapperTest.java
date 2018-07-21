package com.spawpaw.mybatis.generator.gui.test;

import com.spawpaw.mybatis.generator.gui.dao.TestDao;
import com.spawpaw.mybatis.generator.gui.entity.TestEntity;
import com.spawpaw.mybatis.generator.gui.entity.TestExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created By spawpaw@hotmail.com  2018-07-18
 * 测试生成出来的Mapper是否符合mybatis规范（是否能正确运行）
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
@Ignore//跳过自动测试
public class MapperTest {
    private SqlSession session;
    private TestDao mapper;
    private TestUtil testUtil = new TestUtil();

    @Before
    public void initEnvironment() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        Properties properties = new Properties();
        properties.load(Resources.getResourceAsStream("mysql.properties"));
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);

        session = sqlSessionFactory.openSession();
        mapper = session.getMapper(TestDao.class);


        //清空数据
        mapper.deleteByExample(null);//deleteByExample
        Assert.assertEquals(0, mapper.countByExample(null));//countByExample
    }

    //需要进行测试的方法：
    //deleteByExample+
    //countByExample+

    //insert+
    //insertSelective+
    //updateByPrimaryKey+
    //updateByPrimaryKeySelective+
    //deleteByPrimaryKey+
    //batchInsert
    //batchInsertSelective
    //updateByExample
    //updateByExampleSelective

    //selectByPrimaryKey+
    //selectByExample


    @Test
    public void insertAndUpdate() {

        TestEntity testEntity = constructEntityWithRandomParam(); //构造一个随机实体
        Assert.assertEquals("应成功插入一条数据", 1, mapper.insert(testEntity));
        Assert.assertEquals("应成功插入一条数据", 1, mapper.countByExample(new TestExample().or().andIdEqualTo(testEntity.getId()).example()));

        TestEntity testEntityInserted = mapper.selectByPrimaryKey(testEntity.getId());
        compare("插入前后的数据应该相同", testEntity, testEntityInserted);

        String s = "QWERTYUI!@#$%^&*'\\sx阿斯顿";
        testEntityInserted.setField_having_default_value(s);
        testEntityInserted.setField_not_having_default_value(null);
        Assert.assertEquals("应成功更新一条数据", 1, mapper.updateByPrimaryKey(testEntityInserted));
        TestEntity testEntityUpdated = mapper.selectByPrimaryKey(testEntityInserted.getId());
        Assert.assertEquals("非空字段应成功被更新", s, testEntityUpdated.getField_having_default_value());
        Assert.assertEquals("空字段应成功被更新", testEntityInserted.getField_not_having_default_value(), testEntityUpdated.getField_not_having_default_value());

        Assert.assertEquals("应成功删除一条记录", 1, mapper.deleteByPrimaryKey(testEntity.getId()));
    }

    @Test
    public void insertSelectiveAndUpdateSelective() {
        TestEntity testEntity = constructEntityWithRandomParam();
        testEntity.setField_having_default_value(null);
        testEntity.setField_not_having_default_value(null);
        Assert.assertEquals("应成功插入一条数据", 1, mapper.insertSelective(testEntity));
        Assert.assertEquals("应成功插入一条数据", 1, mapper.countByExample(new TestExample().or().andIdEqualTo(testEntity.getId()).example()));

        TestEntity testEntityInserted = mapper.selectByPrimaryKey(testEntity.getId());
        Assert.assertNull("没有默认值的字段插入后应为空", testEntityInserted.getField_not_having_default_value());
        Assert.assertNotNull("有默认值的字段插入后应不为空", testEntityInserted.getField_having_default_value());

        String s = "QWERTYUI!@#$%^&*'\\sx阿斯顿";
        testEntityInserted.setField_having_default_value(null);
        testEntityInserted.setField_not_having_default_value(s);
        Assert.assertEquals("应成功更新一条记录", 1, mapper.updateByPrimaryKeySelective(testEntityInserted));
        TestEntity testEntityUpdated = mapper.selectByPrimaryKey(testEntityInserted.getId());
        Assert.assertNotNull("空字段应不被更新", testEntityUpdated.getField_having_default_value());
        Assert.assertEquals("非空字段应成功被更新", s, testEntityUpdated.getField_not_having_default_value());

        Assert.assertEquals("应成功删除一条记录", 1, mapper.deleteByPrimaryKey(testEntity.getId()));
    }


    /**
     * 构造一个所有属性均为随机的实体
     */
    private TestEntity constructEntityWithRandomParam() {
        return new TestEntity()
//                .withId(testUtil.nextRandomInteger())
                .withName(testUtil.nextRandomString())
                .withField_int(testUtil.nextRandomInteger())
                .withField_boolean(false)
                .withField_double(testUtil.nextRandomDouble())
                .with_fieldTest(testUtil.nextRandomString())
                .with_field_test2(testUtil.nextRandomString())
                .withField_test_(testUtil.nextRandomString())
                .withField_not_having_default_value(testUtil.nextRandomString())
                .withField_having_default_value(testUtil.nextRandomString())
                .withFieldLowerCamel(testUtil.nextRandomString())
                .withFieldUpperCamel(testUtil.nextRandomString())
                .withSINGLE(testUtil.nextRandomString())
                .withFIELD_UPPER_UNDERSCORE(testUtil.nextRandomString())
                .withGroup(testUtil.nextRandomString())
                .withSelect(testUtil.nextRandomString())
                .withDrop(testUtil.nextRandomString())
                .withDeleted(testUtil.nextRandomBoolean());
    }

    private void compare(String msg, TestEntity entity1, TestEntity entity2) {
//        Assert.assertEquals(entity1.getId(), entity2.getId());
        Assert.assertEquals(msg, entity1.get_field_test2(), entity2.get_field_test2());
        Assert.assertEquals(msg, entity1.get_fieldTest(), entity2.get_fieldTest());
        Assert.assertEquals(msg, entity1.getDeleted(), entity2.getDeleted());
        Assert.assertEquals(msg, entity1.getDrop(), entity2.getDrop());
        Assert.assertEquals(msg, entity1.getField_boolean(), entity2.getField_boolean());
        Assert.assertEquals(msg, entity1.getField_double(), entity2.getField_double());
        Assert.assertEquals(msg, entity1.getField_int(), entity2.getField_int());
        Assert.assertEquals(msg, entity1.getField_test_(), entity2.getField_test_());
        Assert.assertEquals(msg, entity1.getFIELD_UPPER_UNDERSCORE(), entity2.getFIELD_UPPER_UNDERSCORE());
        Assert.assertEquals(msg, entity1.getFieldLowerCamel(), entity2.getFieldLowerCamel());
        Assert.assertEquals(msg, entity1.getFieldUpperCamel(), entity2.getFieldUpperCamel());
        Assert.assertEquals(msg, entity1.getGroup(), entity2.getGroup());
        Assert.assertEquals(msg, entity1.getName(), entity2.getName());
        Assert.assertEquals(msg, entity1.getSelect(), entity2.getSelect());
        Assert.assertEquals(msg, entity1.getSINGLE(), entity2.getSINGLE());
        Assert.assertEquals(msg, entity1.getField_having_default_value(), entity2.getField_having_default_value());
        Assert.assertEquals(msg, entity1.getField_not_having_default_value(), entity2.getField_not_having_default_value());
    }

    @After
    public void clean() {
        session.close();
    }
}
