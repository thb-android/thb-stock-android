/*
 * Copyright zh.weir.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thb.automatic.home.fragment.map.entity;

import java.text.DecimalFormat;

/**
 * 新浪股票信息。
 *
 * @author <a href="http://www.blogjava.net/zh-weir/" target="_blank">zh.weir的技术博客</a>
 * </br>
 * </br>
 * <a href="http://weibo.com/1779382071?s=6uyXnP" target="_blank"><img border="0" src="http://service.t.sina.com.cn/widget/qmd/1779382071/9c2d28b9/1.png"/></a>
 */
public class StockInfo {
    /*代码*/
    public String symbol;
    /*名称*/
    public String name;
    /*最新价*/
    public double trade;
    /*涨跌额*/
    public double pricechange;
    /*涨跌幅*/
    public double changepercent;
    /*买一价*/
    public double buy;
    /*卖一价*/
    public double sell;
    /*昨收*/
    public double settlement;
    /*今开*/
    public double open;
    /*最高*/
    public double high;
    /*最低*/
    public double low;
    /*成交量，单位股*/
    public long volume;
    /*成交额，单位元*/
    public double amount;
    /*时间*/
    public String ticktime;
    //日期
    public String mDate;
    //买单情况
    public BuyOrSellInfo[] mBuy;
    //卖单情况
    public BuyOrSellInfo[] mSell;

    public int extraNum;
    public String extraInfo;

    static DecimalFormat df = new DecimalFormat("#.00");

    /**
     * 从一行响应字符串中解析得到SinaStockInfo数据结构。
     *
     * @param source 参数的格式如：
     *               var hq_str_sh601006="大秦铁路,7.69,7.70,7.62,7.72,7.61,7.61,7.62,46358694,355190642,565201,7.61,984000,7.60,211900,7.59,476600,7.58,238500,7.57,295518,7.62,217137,7.63,241500,7.64,345900,7.65,419400,7.66,2012-02-29,15:03:07";
     * @return SinaStockInfo
     * @throws ParseStockInfoException
     */
    public static StockInfo parseStockInfo(String source) throws ParseStockInfoException {
        int start = source.indexOf('\"');
        String targetString = source.substring(start + 1, source.length() - 2);

        String[] infoStr = targetString.split(",");

        if (infoStr.length != 33) {
            throw new ParseStockInfoException();
        }

        StockInfo info = new StockInfo();
        info.symbol = getSymbol(source);
        info.name = infoStr[0];
        info.open = Float.parseFloat(infoStr[1]);
        info.settlement = Float.parseFloat(infoStr[2]);
        info.trade = Float.parseFloat(infoStr[3]);
        info.high = Float.parseFloat(infoStr[4]);
        info.low = Float.parseFloat(infoStr[5]);
        info.buy = Float.parseFloat(infoStr[6]);
        info.sell = Float.parseFloat(infoStr[7]);
        info.volume = Long.parseLong(infoStr[8]);
        info.amount = Float.parseFloat(infoStr[9]);
        info.mDate = infoStr[30];
        info.ticktime = infoStr[31];
        info.pricechange = info.trade - info.settlement;
        info.changepercent = Double.valueOf(df.format((info.pricechange / info.settlement) * 100.0));

        BuyOrSellInfo buy1 = new BuyOrSellInfo(Long.parseLong(infoStr[10]), Float.parseFloat(infoStr[11]));
        BuyOrSellInfo buy2 = new BuyOrSellInfo(Long.parseLong(infoStr[12]), Float.parseFloat(infoStr[13]));
        BuyOrSellInfo buy3 = new BuyOrSellInfo(Long.parseLong(infoStr[14]), Float.parseFloat(infoStr[15]));
        BuyOrSellInfo buy4 = new BuyOrSellInfo(Long.parseLong(infoStr[16]), Float.parseFloat(infoStr[17]));
        BuyOrSellInfo buy5 = new BuyOrSellInfo(Long.parseLong(infoStr[18]), Float.parseFloat(infoStr[19]));

        BuyOrSellInfo sell1 = new BuyOrSellInfo(Long.parseLong(infoStr[20]), Float.parseFloat(infoStr[21]));
        BuyOrSellInfo sell2 = new BuyOrSellInfo(Long.parseLong(infoStr[22]), Float.parseFloat(infoStr[23]));
        BuyOrSellInfo sell3 = new BuyOrSellInfo(Long.parseLong(infoStr[24]), Float.parseFloat(infoStr[25]));
        BuyOrSellInfo sell4 = new BuyOrSellInfo(Long.parseLong(infoStr[26]), Float.parseFloat(infoStr[27]));
        BuyOrSellInfo sell5 = new BuyOrSellInfo(Long.parseLong(infoStr[28]), Float.parseFloat(infoStr[29]));

        info.mBuy = new BuyOrSellInfo[]{buy1, buy2, buy3, buy4, buy5};
        info.mSell = new BuyOrSellInfo[]{sell1, sell2, sell3, sell4, sell5};

        return info;
    }

    private static String getSymbol(String source) {
        int end = source.indexOf('=');
        String temp = source.substring(0, end);
        String[] titleArr = temp.split("_");
        return titleArr[2];
    }

    public static class ParseStockInfoException extends Exception {
        ParseStockInfoException() {
            super("Parse StockInfo error!");
        }
    }

    //买单或卖单信息。
    public static class BuyOrSellInfo {
        //数量。单位为“股”。100股为1手。
        public long mCount;
        //价格。
        public float mPrice;

        public BuyOrSellInfo(long count, float price) {
            mCount = count;
            mPrice = price;
        }
    }
}
