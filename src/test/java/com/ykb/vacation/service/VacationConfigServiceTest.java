package com.ykb.vacation.service;

import com.ykb.vacation.service.VacationConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacationConfigServiceTest {

    @Autowired
    private VacationConfigService vacationConfigService;

    @Test
    void getVacationConfig() {

        long actual = vacationConfigService.getVacationConfig(3).getDayCount();
        long expected = 15;
        assertEquals(actual,expected);
    }

    @Test
    void getVacationConfig2() {

        long actual = vacationConfigService.getVacationConfig(7).getDayCount();
        long expected = 18;
        assertEquals(actual,expected);
    }
}
