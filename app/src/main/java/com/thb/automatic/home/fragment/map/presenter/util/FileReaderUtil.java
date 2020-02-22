package com.thb.automatic.home.fragment.map.presenter.util;

import com.google.gson.Gson;
import com.thb.automatic.home.fragment.map.entity.DateStockInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p><p>
 * <p><p>
 * <p>作者：tanghuaibao<p>
 * <p>创建时间：2020-02-20<p>
 * <p>更改时间：2020-02-20<p>
 * <p>版本号：v1.0.0<p>
 */
public class FileReaderUtil {

    private Gson gson;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private static FileReaderUtil instance;

    private FileReaderUtil(File file) {
        gson = new Gson();

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileReaderUtil getInstance(File file) {
        if (instance == null) {
            synchronized (FileReaderUtil.class) {
                if (null == instance) {
                    instance = new FileReaderUtil(file);
                }
            }
        }
        return instance;
    }

    public List<DateStockInfo> getAllStockInfo() {
        List<DateStockInfo> infos = new ArrayList<>();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                DateStockInfo info = gson.fromJson(line, DateStockInfo.class);
                infos.add(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return infos;
    }

    public void clear() {
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileReader = null;
        bufferedReader = null;
        instance = null;
    }
}
