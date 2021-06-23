package com.ykb.vacation.service;

import com.ykb.vacation.dto.VacationRequest;
import com.ykb.vacation.dto.VoidResponse;
import com.ykb.vacation.entity.User;
import com.ykb.vacation.entity.Vacation;
import com.ykb.vacation.enums.ApprovalType;

import java.util.Date;

public interface VacationService {

    VoidResponse create(VacationRequest vacationRequest);

    VoidResponse create(User user, Date startDate, Date endDate);

    VoidResponse approval(Vacation vacation, ApprovalType approvalType);
}
