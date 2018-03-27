package com.spawpaw.mybatis.generator.gui.util;

import com.google.gson.Gson;
import org.hildan.fxgson.FxGsonBuilder;

import java.io.*;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 文件操作的工具类
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class FileUtil {
    public static String readFileAsStr(String path) {
        return readFileAsStr(new File(path));
    }

    public static String readFileAsStr(File file) {
        StringBuilder sb = new StringBuilder();
        String tempstr = null;
        try {
            if (!file.exists())
                throw new FileNotFoundException();
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
            while ((tempstr = br.readLine()) != null) {
                sb.append(tempstr).append("\r\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static <T> void writeJsonObjToFile(String filePath, T clazz) throws IOException {
        Gson gson = new FxGsonBuilder().create();
        writeStringToFile(filePath, gson.toJson(clazz));
    }

    public static void writeStringToFile(String filePath, String content) throws IOException {
        File file = new File(filePath);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"));
        writer.write(content);
        writer.close();
    }
}