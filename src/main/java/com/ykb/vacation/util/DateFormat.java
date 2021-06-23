package com.ykb.vacation.util;

import com.ykb.vacation.dto.VoidResponse;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateFormat {

    public static Date getYearFirst() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getYearLast() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 11); // 11 = aralik
        cal.set(Calendar.DAY_OF_MONTH, 31); // son gunu
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static long dateBetweenCount(Date start, Date end) {
        long a = 0;
        for (Date date : getDatesBetween(start, end)) {
            if (!isWeekend(date)) a++;
        }
        return a;
    }

    private static List<Date> getDatesBetween(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }

    public static long dateBetweenYearCount(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) / 365l;
    }

    public static VoidResponse dateControl(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return new VoidResponse(true, "date.null");

        if (startDate.getTime() < new Date().getTime())
            return new VoidResponse(true, "date.before");

        if (startDate.getTime() > endDate.getTime())
            return new VoidResponse(true, "date.end.before");

        return new VoidResponse(false, "date.success");
    }

    public static boolean isWeekend(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
            return true;
        }
        return false;
    }
}
