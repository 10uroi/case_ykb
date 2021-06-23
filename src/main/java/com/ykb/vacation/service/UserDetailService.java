package com.ykb.vacation.service;

import com.ykb.vacation.entity.User;

import java.util.Date;

public interface UserDetailService {

    boolean isVacationDuplicate(User user, Date startDate, Date endDate);

    long getWorkYear(User user);
}
