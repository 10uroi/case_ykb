package com.ykb.vacation.service.imp;

import com.ykb.vacation.entity.VacationConfig;
import com.ykb.vacation.repository.VacationConfigRepository;
import com.ykb.vacation.service.VacationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class VacationConfigServiceImp implements VacationConfigService {

    @Autowired
    private VacationConfigRepository vacationConfigRepository;

    @EventListener(ApplicationStartedEvent.class)
    private void setVacationConfig() {
        if (vacationConfigRepository.count() > 0) return;

        vacationConfigRepository.save(new VacationConfig(0, 0, 5));
        vacationConfigRepository.save(new VacationConfig(1, 5, 15));
        vacationConfigRepository.save(new VacationConfig(6, 10, 18));
        vacationConfigRepository.save(new VacationConfig(11, 100, 24));
    }

    @Override
    public VacationConfig getVacationConfig(long workYear) {
        return vacationConfigRepository.getByStartYearBetweenAndEndYear(Integer.parseInt(workYear + ""));
    }

}
