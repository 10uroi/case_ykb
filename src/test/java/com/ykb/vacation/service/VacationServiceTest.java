package com.ykb.vacation.service;

import com.ykb.vacation.dto.VoidResponse;
import com.ykb.vacation.entity.User;
import com.ykb.vacation.service.imp.VacationServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacationServiceTest {

    @Autowired
    private VacationServiceImp vacationServiceImp;

    @Test
    void vacationDuplicate() {
        User user = new User();
        user.setId(1l);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 5, 10, 0, 0, 0);
        Date start = calendar.getTime();
        calendar.set(2021, 5, 17, 0, 0, 0);
        Date end = calendar.getTime();

        VoidResponse actual = vacationServiceImp.vacationDuplicate(user, start, end);
        VoidResponse expected = new VoidResponse(false);

        assertEquals(actual, expected);
    }
}
