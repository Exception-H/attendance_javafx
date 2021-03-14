package org.example.utils;

/**
 * @program: attendance
 * @description:
 * @author: Curtain
 * @create: 2021-03-14 12:08
 */
public class WeekDayUtil {
    /**
     * 计算某天星期几
     * 在计算此问题上比较常用的还是基姆拉尔森计算公式
     W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
     d 天、m 月、y 年
     1月2月换算为去年的13、14月计算，
     也即2007年的1月和2月在公式中体现为2006年的13和14月，
     虽然与实际不符但這是逻辑的需要。
     * @param y 年、m 月、d 日
     * @return String
     */
    public static String count(int y,int m,int d)
    {
        if(m==1) {m=13;y--;}
        if(m==2) {m=14;y--;}
        int week=(d+2*m+3*(m+1)/5+y+y/4-y/100+y/400)%7;
        String weekstr="";
        switch(week)
        {
            case 0: weekstr="一"; break;
            case 1: weekstr="二"; break;
            case 2: weekstr="三"; break;
            case 3: weekstr="四"; break;
            case 4: weekstr="五"; break;
            case 5: weekstr="六"; break;
            case 6: weekstr="日"; break;
        }
        return weekstr;
    }
}
