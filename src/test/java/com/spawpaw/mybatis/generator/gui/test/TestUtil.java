package com.spawpaw.mybatis.generator.gui.test;

import org.apache.commons.lang.text.StrBuilder;

import java.util.Random;

/**
 * Created By spawpaw@hotmail.com  2018-07-21
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TestUtil {
    public final String RANDOM_STRING_SOURCES = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890" +
            "去我额人他有ui哦怕啊是的的发给和就看了在下从v吧你吗，。·/；‘【】、《》？：”{}|！@#￥%……&*（）——+-=" +
            "`!@#$%^&*()_+-=[]\\;',./<>?:\"{}|";
    private Random random;

    public TestUtil() {
        this(System.currentTimeMillis());
    }

    public TestUtil(long seed) {
        this.random = new Random(seed);
    }

    public Integer nextRandomInteger() {
        return random.nextInt();
    }

    public Double nextRandomDouble() {
        return random.nextDouble();
    }

    public boolean nextRandomBoolean() {
        return random.nextInt() % 2 == 0;
    }

    public String nextRandomString() {
        return nextRandomString(0, 32);
    }

    /**
     * 使用默认随机字符串源构造某个长度范围内的随机字符串
     *
     * @param lengthRange1 下界
     * @param lengthRange2 上界
     */
    public String nextRandomString(int lengthRange1, int lengthRange2) {
        int length = lengthRange1 + random.nextInt() % (lengthRange2 - lengthRange1);
        return generateString(RANDOM_STRING_SOURCES, length);
    }

    /**
     * Generate a random string.
     *
     * @param characters the characters for generating string.
     * @param length     the length of the generated string.
     * @return
     */
    private String generateString(String characters, int length) {
        StrBuilder strBuilder = new StrBuilder();
        for (int i = 0; i < length; i++) {
            strBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return strBuilder.toString();
    }
}
