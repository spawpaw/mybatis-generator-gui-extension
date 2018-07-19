package com.spawpaw.mybatis.generator.gui.test;

import com.spawpaw.mybatis.generator.gui.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created By spawpaw@hotmail.com  2018-07-18
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class FileUtilTest {
    @Test
    public void testReadFileAsString() {
        Assert.assertEquals("this is test string, 哈哈哈，，，！@#￥%……&*（）!@#$%^&*()_\r\n" +
                        "safdsfhsad\r\n",
                FileUtil.readFileAsStr("src/test/resources/testFile.txt")
        );
    }
}
