package com.spawpaw.mybatis.generator.gui.test;

import com.google.common.base.CaseFormat;
import com.spawpaw.mybatis.generator.gui.util.Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created By spawpaw@hotmail.com  2018-07-18
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class CaseFormatTest {

    @Test
    public void testCaseFormat() {
        CaseFormat caseFormat[] = {CaseFormat.LOWER_CAMEL, CaseFormat.UPPER_CAMEL, /*CaseFormat.LOWER_UNDERSCORE, CaseFormat.UPPER_UNDERSCORE, CaseFormat.LOWER_HYPHEN*/};
        String formatsCases[][][] = {
                //LOWER_CAMEL
                {
                        //0为期望的值，其余的为测试的值
                        {"camelCase", "Camel-Case", "CamelCase-", "Camel-Case-", "Camel_Case", "CamelCase_", "Camel_Case_", "CAMEL_CASE", "CAMEL_CASE_", "CAMEL_CASE__", "CAMEL_CASE"},
                        {"camel", "camel_", "Camel", "camel-", "CAMEL", "CAMEL_"},
                        {"_camelCase"},
                        {"_camel"},
                        {"_camel_CASE"},
                        {"_camel_CaSE_"},
                },
                //UPPER_CAMEL
                {
                        {"CamelCase", "Camel-Case", "CamelCase-", "Camel-Case-", "Camel_Case", "CamelCase_", "Camel_Case_", "CAMEL_CASE", "CAMEL_CASE_", "CAMEL_CASE__", "CAMEL_CASE"},
                        {"Camel", "camel_", "Camel", "camel-", "CAMEL", "CAMEL_"},
                        {"_camelCase"},
                        {"_camel"},
                        {"_camel_CASE"},
                        {"_camel_CAsE_"},
                },
                //LOWER_UNDERSCORE
                {

                },
                //UPPER_UNDERSCORE
                {

                },
                //LOWER_HYPHEN
                {

                },
        };

        for (int j = 0; j < formatsCases.length; j++)
            for (String[] testCases : formatsCases[j])
                for (String testCase : testCases) {
                    String expected = testCases[0];
                    String actual = testCase;
                    //actual = CaseFormat.UPPER_CAMEL.to(caseFormat[i],actual);//在使用不标准的命名方式时，直接使用guava提供的方式进行转换会出现一些问题

                    switch (caseFormat[j]) {
                        case LOWER_CAMEL:
                            actual = Utils.getLowerCamelCase(actual);
                            break;
                        case UPPER_CAMEL:
                            actual = Utils.getUpperCamelCase(actual);
                            break;
                        default:
                            break;
//                            throw new RuntimeException("unsupported case format");
                    }
                    System.out.printf("%-16s: %-16s [%s]=> %s\n", expected, testCase, caseFormat[j].toString(), actual);
                    Assert.assertEquals(expected, actual);
                }
    }

}
