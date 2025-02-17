package com.aier.cloud.api.amcs.utils;

import java.time.LocalDateTime;
import java.time.Month;

public class TimePeriodUtil {

    static class TimePeriod {
        int startMonth;
        int endMonth;

        TimePeriod(int startMonth, int endMonth) {
            this.startMonth = startMonth;
            this.endMonth = endMonth;
        }

        boolean isInPeriod(LocalDateTime dateTime) {
            return dateTime.getMonthValue() >= startMonth && dateTime.getMonthValue() <= endMonth;
        }
    }

    public static String getCurDatePeriod(LocalDateTime now){
        String curQuarter = "";

        TimePeriod[] periods = new TimePeriod[] {
                new TimePeriod(Month.JANUARY.getValue(), Month.MARCH.getValue()), // 1月1日00:00至3月31日23:59
                new TimePeriod(Month.APRIL.getValue(), Month.JUNE.getValue()), // 4月1日00:00至6月30日23:59
                new TimePeriod(Month.JULY.getValue(), Month.SEPTEMBER.getValue()), // 7月1日00:00至9月30日23:59
                new TimePeriod(Month.OCTOBER.getValue(), Month.DECEMBER.getValue()) // 10月1日00:00至12月31日23:59
        };

        for (int i = 0; i < periods.length; i++) {
            if (periods[i].isInPeriod(now)) {
                // System.out.println("当前时间属于时间段: " + (i + 1));
                curQuarter = now.getYear() + "" + (i + 1);
                break;
            }
        }

        return curQuarter;
    }

    public static String getPreviousDatePeriod(LocalDateTime now) {
        int currentYear = now.getYear();
        int previousYear;
        int previousQuarter;

        String curQuarter = getCurDatePeriod(now);
        int currentQuarter = Integer.parseInt(curQuarter.substring(curQuarter.length() - 1));

        if (currentQuarter == 1) {
            previousQuarter = 4;
            previousYear = currentYear - 1;
        } else {
            previousQuarter = currentQuarter - 1;
            previousYear = currentYear;
        }

        return previousYear + "" + previousQuarter;
    }

}
