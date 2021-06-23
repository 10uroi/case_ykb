package com.ykb.vacation.service;

import com.ykb.vacation.dto.VacationRequest;
import com.ykb.vacation.dto.VoidResponse;
import com.ykb.vacation.entity.User;

import java.util.Date;

public interface VacationService {

    VoidResponse create(VacationRequest vacationRequest);

    VoidResponse create(User user, Date startDate, Date endDate);
}
