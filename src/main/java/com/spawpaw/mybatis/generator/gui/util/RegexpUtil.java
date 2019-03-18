package com.spawpaw.mybatis.generator.gui.util;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpUtil {
    /**
     * @param prefix     前定界
     * @param catchGroup 捕获组
     * @param suffix     后定界
     * @return 寻找到的第一个组
     */
    public static String findMatches(String prefix, String catchGroup, String suffix, String findFrom) {
        if (findFrom == null) {
            return "";
        }
        String regexp = prefix + catchGroup + suffix;
        System.out.printf("从%s中查找%s", findFrom, regexp);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(findFrom);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static List<String> findAllMatches(String prefix, String catchGroup, String suffix, String findFrom) {
        List<String> result = new LinkedList<>();
        String regexp = prefix + catchGroup + suffix;
        if (findFrom == null) {
            return result;
        }
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(findFrom);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }
}
