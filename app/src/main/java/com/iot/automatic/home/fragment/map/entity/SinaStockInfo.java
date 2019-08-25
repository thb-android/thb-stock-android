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
package com.iot.automatic.home.fragment.map.entity;

/**
 * 新浪股票信息。
 *
 * @author <a href="http://www.blogjava.net/zh-weir/" target="_blank">zh.weir的技术博客</a>
 * </br>
 * </br>
 * <a href="http://weibo.com/1779382071?s=6uyXnP" target="_blank"><img border="0" src="http://service.t.sina.com.cn/widget/qmd/1779382071/9c2d28b9/1.png"/></a>
 */
public class SinaStockInfo {

    //股票名字
    private String mName;
    //今日开盘价
    private float mTodayPrice;
    //昨日收盘价
    private float mYestodayPrice;
    //当前价
    private float mNowPrice;
    //今日最高价
    private float mHighestPrice;
    //今日最低价
    private float mLowestPrice;
    //买一价
    private float mBuy1Price;
    //卖一价
    private float mSell1Price;
    //成交股票数，单位“股”。100股为1手。
    private long mTradeCount;
    //成交额，单位“元”。一般需要转换成“万元”。
    private float mTradeMoney;

    //买单情况
    private BuyOrSellInfo[] mBuy;

    //卖单情况
    private BuyOrSellInfo[] mSell;

    //日期
    private String mDate;
    //时间
    private String mTime;

    private SinaStockInfo(String name, float todayPrice, float yestodayPrice,
                          float nowPrice, float highestPrice, float lowestPrice,
                          float buy1Price, float sell1Price, long tradeCount, float tradeMoney,
                          BuyOrSellInfo[] buy, BuyOrSellInfo[] sell, String date, String time) {

        mName = name;
        mTodayPrice = todayPrice;
        mYestodayPrice = yestodayPrice;

        mNowPrice = nowPrice;
        mHighestPrice = highestPrice;
        mLowestPrice = lowestPrice;

        mBuy1Price = buy1Price;
        mSell1Price = sell1Price;

        mTradeCount = tradeCount;
        mTradeMoney = tradeMoney;

        mBuy = buy;
        mSell = sell;

        mDate = date;
        mTime = time;
    }

    /**
     * 从一行响应字符串中解析得到SinaStockInfo数据结构。
     *
     * @param source 参数的格式如：
     *               var hq_str_sh601006="大秦铁路,7.69,7.70,7.62,7.72,7.61,7.61,7.62,46358694,355190642,565201,7.61,984000,7.60,211900,7.59,476600,7.58,238500,7.57,295518,7.62,217137,7.63,241500,7.64,345900,7.65,419400,7.66,2012-02-29,15:03:07";
     * @return SinaStockInfo
     * @throws ParseStockInfoException
     */
    public static SinaStockInfo parseStockInfo(String source) throws ParseStockInfoException {
        int start = source.indexOf('\"');
        String targetString = source.substring(start + 1, source.length() - 2);

        String[] infoStr = targetString.split(",");

        if (infoStr.length != 33) {
            throw new ParseStockInfoException();
        }

        final String name = infoStr[0];
        final float todayPrice = Float.parseFloat(infoStr[1]);
        final float yestodayPrice = Float.parseFloat(infoStr[2]);
        final float nowPrice = Float.parseFloat(infoStr[3]);
        final float highestPrice = Float.parseFloat(infoStr[4]);
        final float lowestPrice = Float.parseFloat(infoStr[5]);
        final float buy1Price = Float.parseFloat(infoStr[6]);
        final float sell1Price = Float.parseFloat(infoStr[7]);
        final long tradeCount = Long.parseLong(infoStr[8]);
        final float tradeMoney = Float.parseFloat(infoStr[9]);

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

        final String date = infoStr[30];
        final String time = infoStr[31];

        return new SinaStockInfo(name, todayPrice, yestodayPrice, nowPrice,
                highestPrice, lowestPrice, buy1Price, sell1Price,
                tradeCount, tradeMoney,
                new BuyOrSellInfo[]{buy1, buy2, buy3, buy4, buy5},
                new BuyOrSellInfo[]{sell1, sell2, sell3, sell4, sell5},
                date, time);
    }

    /**
     * 获取股票名称
     *
     * @return 股票名称
     */
    public String getName() {
        return mName;
    }

    /**
     * 获取今日开盘价
     *
     * @return 今日股票开盘价
     */
    public float getTodayPrice() {
        return mTodayPrice;
    }

    /**
     * 获取昨日收盘价
     *
     * @return 昨日收盘价
     */
    public float getYestodayPrice() {
        return mYestodayPrice;
    }

    /**
     * 获取当前股价
     *
     * @return 当前股价
     */
    public float getNowPrice() {
        return mNowPrice;
    }

    /**
     * 获取今日最高价
     *
     * @return 今日最高价
     */
    public float getHighestPrice() {
        return mHighestPrice;
    }

    /**
     * 获取今日最低价
     *
     * @return 今日最低价
     */
    public float getLowestPrice() {
        return mLowestPrice;
    }

    /**
     * 获取股票交易量。单位为“股”，100股为1手，请注意转换。
     *
     * @return 股票交易量
     */
    public long getTradeCount() {
        return mTradeCount;
    }

    /**
     * 获取股票交易额。单位为“元”，如需显示“万元”，请注意转换。
     *
     * @return 股票交易额
     */
    public float getTradeMoney() {
        return mTradeMoney;
    }

    /**
     * 获取买手信息。
     *
     * @return 买手信息。数组从前到后依次为：买一、买二、买三、买四、买五。
     */
    public BuyOrSellInfo[] getBuyInfo() {
        return mBuy;
    }

    /**
     * 获取卖手信息。
     *
     * @return 卖手信息。数组从前到后依次为：卖一、卖二、卖三、卖四、卖五。
     */
    public BuyOrSellInfo[] getSellInfo() {
        return mSell;
    }

    /**
     * 获取对应股票信息的日期。例如周末，或者其他休市期间获取的数据将不是实时的。
     *
     * @return 获取对应股票信息的日期。
     */
    public String getDate() {
        return mDate;
    }

    /**
     * 获取对应股票信息的时间。例如周末，或者其他休市期间获取的数据将不是实时的。
     *
     * @return 获取对应股票信息的时间。
     */
    public String getTime() {
        return mTime;
    }


    public static class ParseStockInfoException extends Exception {
        public ParseStockInfoException() {
            super("Parse StockInfo error!");
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        sb.append("股票名称： " + getName() + "\n");
        sb.append("今日开盘价： " + getTodayPrice() + "元\n");
        sb.append("昨日收盘价： " + getYestodayPrice() + "元\n");
        sb.append("当前股价： " + getNowPrice() + "元\n");
        sb.append("今日最高价： " + getHighestPrice() + "元\n");
        sb.append("今日最低价： " + getLowestPrice() + "元\n");
        sb.append("今日交易量： " + getTradeCount() + "股\n");
        sb.append("今日成交量： " + getTradeMoney() + "元\n");

        BuyOrSellInfo[] buy = getBuyInfo();
        sb.append("买一：\n" + buy[0] + "\n");
        sb.append("买二：\n" + buy[1] + "\n");
        sb.append("买三：\n" + buy[2] + "\n");
        sb.append("买四：\n" + buy[3] + "\n");
        sb.append("买五：\n" + buy[4] + "\n");

        BuyOrSellInfo[] sell = getSellInfo();
        sb.append("卖一：\n" + sell[0] + "\n");
        sb.append("卖二：\n" + sell[1] + "\n");
        sb.append("卖三：\n" + sell[2] + "\n");
        sb.append("卖四：\n" + sell[3] + "\n");
        sb.append("卖五：\n" + sell[4] + "\n");

        sb.append("时间： " + getTime() + "\n");
        sb.append("日期： " + getDate() + "\n");

        return sb.toString();
    }


    //买单或卖单信息。
    private static class BuyOrSellInfo {
        //数量。单位为“股”。100股为1手。
        long mCount;
        //价格。
        float mPrice;

        public BuyOrSellInfo(long count, float price) {
            mCount = count;
            mPrice = price;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("数量： " + mCount + "股    ");
            sb.append("价格： " + mPrice + "元");
            return sb.toString();
        }
    }
}
