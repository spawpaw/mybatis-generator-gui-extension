package com.spawpaw.mybatis.generator.gui.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 文件操作的工具类
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class FileUtil {
    public static String readFileByStr(String path) {
        return readFileByStr(new File(path));
    }

    public static String readFileByStr(File file) {
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

    public static void writeStringToFile(String filePath, String content) throws IOException {
        File file = new File(filePath);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        Files.write(Paths.get(filePath), content.getBytes());
    }

    public static void writeObjectToFile(String path, Object obj) {
        File file = new File(path);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
    }

    public static Object readObjectFromFile() {
        Object temp = null;
        File file = new File("test.dat");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(in);
            temp = objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }


    /**
     * Serialize the provided object to the file of the provided name.
     *
     * @param objectToSerialize Object that is to be serialized to file; it is
     *                          best that this object have an individually overridden toString()
     *                          implementation as that is used by this method for writing our status.
     * @param fileName          Name of file to which object is to be serialized.
     * @throws IllegalArgumentException Thrown if either provided parameter is null.
     */
    public static <T> void serialize(final String fileName, final T objectToSerialize) {
        if (fileName == null) {
            throw new IllegalArgumentException(
                    "Name of file to which to serialize object to cannot be null.");
        }
        if (objectToSerialize == null) {
            throw new IllegalArgumentException("Object to be serialized cannot be null.");
        }
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(objectToSerialize);
            System.out.println("Serialization of Object " + objectToSerialize + " completed.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Provides an object deserialized from the file indicated by the provided
     * file name.
     *
     * @param <T>                    Type of object to be deserialized.
     * @param fileToDeserialize      Name of file from which object is to be deserialized.
     * @param classBeingDeserialized Class definition of object to be deserialized
     *                               from the file of the provided name/path; it is recommended that this
     *                               class define its own toString() implementation as that will be used in
     *                               this method's status output.
     * @return Object deserialized from provided filename as an instance of the
     * provided class; may be null if something goes wrong with deserialization.
     * @throws IllegalArgumentException Thrown if either provided parameter is null.
     */
    public static <T> T deserialize(final String fileToDeserialize, final Class<T> classBeingDeserialized) {
        if (fileToDeserialize == null) {
            throw new IllegalArgumentException("Cannot deserialize from a null filename.");
        }
        if (classBeingDeserialized == null) {
            throw new IllegalArgumentException("Type of class to be deserialized cannot be null.");
        }
        T objectOut = null;
        try (FileInputStream fis = new FileInputStream(fileToDeserialize);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            objectOut = (T) ois.readObject();
            System.out.println("Deserialization of Object " + objectOut + " is completed.");
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return objectOut;

    }
}