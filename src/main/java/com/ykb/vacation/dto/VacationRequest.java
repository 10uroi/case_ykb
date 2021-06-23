package com.ykb.vacation.dto;

import com.ykb.vacation.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class VacationRequest {

    private User user;
    private Date startDate;
    private Date endDate;

}
