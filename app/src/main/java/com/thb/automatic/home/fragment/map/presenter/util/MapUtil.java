package com.thb.automatic.home.fragment.map.presenter.util;

import com.thb.automatic.app.utils.Utils;
import com.thb.automatic.home.fragment.map.entity.StockInfo;
import com.thb.automatic.home.fragment.map.model.DateStockInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p><p>
 * <p>获取30日涨幅<p>
 * <p>作者：tanghuaibao<p>
 * <p>创建时间：2020-02-20<p>
 * <p>更改时间：2020-02-20<p>
 * <p>版本号：v1.0.0<p>
 */
public class MapUtil {

    private static MapUtil instance;

    private List<Map<String, Double>> dateStockInfos = new ArrayList<>();

    private MapUtil(List<StockInfo> infos) {
        List<File> files = getFiles();

        // 30代表30天，只取30天数据
        final int size = files.size() > 30 ? 30 : files.size();
        for (int i = 0; i < size; i++) {
            List<DateStockInfo> fileStockInfo = FileReaderUtil.getInstance(files.get(i)).getAllStockInfo();
            FileReaderUtil.getInstance(files.get(i)).clear();
            HashMap<String, Double> temp = new HashMap<>();
            if (null != infos && null != fileStockInfo) {
                for (StockInfo info : infos) {
                    for (DateStockInfo dateStockInfo : fileStockInfo) {
                        if (dateStockInfo.symbol.equals(info.symbol)) {
                            temp.put(dateStockInfo.symbol, dateStockInfo.changepercent);
                        }
                    }
                }
            }
            dateStockInfos.add(temp);
        }
    }

    public static MapUtil getInstance(List<StockInfo> infos) {
        if (instance == null) {
            instance = new MapUtil(infos);
        }
        return instance;
    }

    public List<Map<String, Double>> getStockInfos() {
        return dateStockInfos;
    }

    private List<File> getFiles() {
        String dir = Utils.getDateDirectory();
        File dirFile = new File(dir);
        File[] files = dirFile.listFiles();

        List<File> fileList = new ArrayList<File>(Arrays.asList(files));

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o2.getName().compareTo(o1.getName());
            }
        });

        return fileList;
    }
}
