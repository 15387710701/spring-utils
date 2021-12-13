package com.ms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

/**
 * @author:SmartV
 * @date:2021/12/13 12:38
 */
public class DateUtils {
    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean isInTime(String nowTime, String beginTime, String endTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date dateNowTime = null;
        Date dateBegin = null;
        Date dateEndTime = null;
        try {
            dateEndTime = simpleDateFormat.parse(endTime);
            dateNowTime = simpleDateFormat.parse(nowTime);
            dateBegin = simpleDateFormat.parse(beginTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("开始时间是:" + dateBegin);
        System.out.println("结束时间是:" + dateEndTime);
        Calendar date = Calendar.getInstance();
        date.setTime(dateNowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(dateBegin);
        Calendar end = Calendar.getInstance();
        end.setTime(dateEndTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查看当天是星期几
     *
     * @param str
     * @throws ParseException
     */
    public static int getWeekFromStrDateByCalendar(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Asia/Shanghai")));
        instance.setTime(parse);
        int i = instance.get(Calendar.DAY_OF_WEEK);
        if ( i-1==0){
            return 7;
        }
        return  i-1;
    }

    /**
     * 获取每月的天数
     * @param year
     * @param month
     * @return 返回类型  {'2021-01-01','2021-01-02','2021-01-03'}
     */
    public static List<String> getDataStringList(String year, String month) {
        List<String> dataList = new ArrayList<>();
        List<String> maxMonth = getMaxMonth();
        List<String> minMonth = getMinMonth();
        if (maxMonth.contains(month)) {
            for (int i = 1; i <= 31; i++) {
                String value = String.valueOf(i);
                String data = value.length() > 1 ? year + "-" + month + "-" + value : year + "-" + month + "-" + "0" + value;
                dataList.add(data);
            }
        } else if (minMonth.contains(month)) {
            for (int i = 1; i <= 30; i++) {
                String value = String.valueOf(i);
                String data = value.length() > 1 ? year + "-" + month + "-" + value : year + "-" + month + "-" + "0" + value;
                dataList.add(data);
            }
        } else {
            int anInt = Integer.parseInt(year);
            if (anInt % 4 == 0 && anInt % 100 != 0) {
                for (int i = 1; i <= 29; i++) {
                    String value = String.valueOf(i);
                    String data = value.length() > 1 ? year + "-" + month + "-" + value : year + "-" + month + "-" + "0" + value;
                    dataList.add(data);
                }
            } else {
                for (int i = 1; i <= 28; i++) {
                    String value = String.valueOf(i);
                    String data = value.length() > 1 ? year + "-" + month + "-" + value : year + "-" + month + "-" + "0" + value;
                    dataList.add(data);
                }
            }

        }
        return dataList;
    }


    // 拿到大月月份
    private static List<String> getMaxMonth() {
        List<String> MaxMonth = new ArrayList<>();
        MaxMonth.add("01");
        MaxMonth.add("03");
        MaxMonth.add("05");
        MaxMonth.add("07");
        MaxMonth.add("08");
        MaxMonth.add("10");
        MaxMonth.add("12");
        return MaxMonth;
    }
    //拿到小月月分
    private static List<String> getMinMonth() {
        List<String> MinMonth = new ArrayList<>();
        MinMonth.add("04");
        MinMonth.add("06");
        MinMonth.add("09");
        MinMonth.add("11");
        return MinMonth;
    }
}
