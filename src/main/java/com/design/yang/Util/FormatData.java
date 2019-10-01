package com.design.yang.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-28 15:24
 */
public class FormatData {
    public static List<String> getMonths(){
        Calendar cal = Calendar.getInstance();
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH);
        int year = nowYear - 1;
        int month = nowMonth + 2;
        List<String> list = new ArrayList<>();
        for(int i = 0 ; i < 12 ; i++){
            if(month == 13){
                year ++;
                month = 1;
            }
            String time = year+"-"+String.format("%02d", month);
            month++;
            list.add(time);
        }
        return list;
    }
}
