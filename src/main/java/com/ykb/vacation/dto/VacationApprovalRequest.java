package com.ykb.vacation.dto;

import com.ykb.vacation.entity.User;
import com.ykb.vacation.entity.Vacation;
import com.ykb.vacation.enums.ApprovalType;
import lombok.Data;

import java.util.Date;

@Data
public class VacationApprovalRequest {

    private Vacation vacation;
    private ApprovalType approvalType;

}
