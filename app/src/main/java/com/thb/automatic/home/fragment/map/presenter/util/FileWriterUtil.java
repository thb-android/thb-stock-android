package com.thb.automatic.home.fragment.map.presenter.util;

import com.google.gson.Gson;
import com.thb.automatic.app.utils.Utils;
import com.thb.automatic.home.fragment.map.entity.DateStockInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p><p>
 * <p><p>
 * <p>作者：tanghuaibao<p>
 * <p>创建时间：2020-02-19<p>
 * <p>更改时间：2020-02-19<p>
 * <p>版本号：v1.0.0<p>
 */
public class FileWriterUtil {

    private Gson gson;
    private FileWriter fileWriter;
    private static FileWriterUtil instance;

    private FileWriterUtil(String date) {
        gson = new Gson();

        File dir = new File(Utils.getDateDirectory());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(Utils.getFileByDate(date));
        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
            fileWriter = new FileWriter(Utils.getFileByDate(date), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileWriterUtil getInstance(String date) {
        if (instance == null) {
            synchronized (FileWriterUtil.class) {
                if (null == instance) {
                    instance = new FileWriterUtil(date);
                }
            }
        }
        return instance;
    }

    public void write2Sd(DateStockInfo info) {
        try {
            fileWriter.write(gson.toJson(info));
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        try {
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileWriter = null;
        instance = null;
    }
}
