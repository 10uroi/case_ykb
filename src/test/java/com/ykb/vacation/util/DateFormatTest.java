package com.ykb.vacation.util;

import com.ykb.vacation.dto.VoidResponse;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateFormatTest {

    @Test
    void getYearFirst() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 0, 1, 0, 0, 0);
        Date start = calendar.getTime();

        assertEquals(start.toString(), DateFormat.getYearFirst().toString());
    }

    @Test
    void getYearLast() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11, 31, 23, 59, 59);
        Date start = calendar.getTime();

        assertEquals(start.toString(), DateFormat.getYearLast().toString());
    }

    @Test
    void dateBetweenCount() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 5, 10, 0, 0, 0);
        Date start = calendar.getTime();
        calendar.set(2021, 5, 17, 0, 0, 0);
        Date end = calendar.getTime();

        long actual = DateFormat.dateBetweenCount(start, end);
        long expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    void dateBetweenYearCount() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 5, 10, 0, 0, 0);
        Date start = calendar.getTime();
        calendar.set(2024, 5, 17, 0, 0, 0);
        Date end = calendar.getTime();

        long actual = DateFormat.dateBetweenYearCount(start, end);
        long expected = 3;

        assertEquals(actual, expected);
    }

    @Test
    void dateControl() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 8, 10, 0, 0, 0);
        Date start = calendar.getTime();
        calendar.set(2021, 8, 17, 0, 0, 0);
        Date end = calendar.getTime();

        VoidResponse actual = DateFormat.dateControl(start, end);
        VoidResponse expected = new VoidResponse(false, "date.success");

        assertEquals(actual, expected);

    }

    @Test
    void isWeekend() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 5, 13, 0, 0, 0);
        Date date = calendar.getTime();

        boolean actual = DateFormat.isWeekend(date);
        boolean expected = true;

        assertEquals(actual, expected);
    }
}
