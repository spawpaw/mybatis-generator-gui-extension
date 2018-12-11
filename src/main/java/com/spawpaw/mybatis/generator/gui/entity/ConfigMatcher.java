package com.spawpaw.mybatis.generator.gui.entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 匹配表/字段中形如 #key(value,...) 的注释，添加到模版的变量中
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class ConfigMatcher extends HashMap<String, String> {
    protected static final String configPrefix = "#";
    protected static final String configPattern = "(" + configPrefix + "[^(]+\\([^)]*\\))";

    Logger log = LoggerFactory.getLogger(ConfigMatcher.class);

    public ConfigMatcher(String remarks, Map<String, String> parent) {
        if (parent != null)
            putAll(parent);
        init(remarks);
    }

    public static void main(String[] args) {
        new ConfigMatcher("#com.spawpaw()#label(你好呀)#column.search(equal,like,likeIgnoreCase,between,larger,less)", null);
    }

    /**
     * 提取字符串中的配置信息
     *
     * @param remarks 表或字段的注释
     */
    public void init(String remarks) {
        // 按指定模式在字符串查找
        Pattern r = Pattern.compile(configPattern);
        Matcher m = r.matcher(remarks);
        while (m.find()) {
            String s = m.group(1);
            String key = s.split("\\(")[0].replace(configPrefix, "");
            String value = s.split("\\(")[1].replace(")", "");

            String[] params = value.split(",");
            for (String param : params) {
                putIfAbsent(key + "." + param, "");
                log.info("find config: {}.{} \t-> {}", key, param, "\"\"");
            }
            putIfAbsent(key, value);
            log.info("find config: {} \t\t-> {}", key, value);
        }
    }


    public boolean contains(String key) {
        return containsKey(key);
    }

    public String get(String key) {
        return super.get(key);
    }
}
