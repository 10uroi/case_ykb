package com.ykb.vacation.service;

import com.ykb.vacation.dto.VoidResponse;
import com.ykb.vacation.entity.User;

public interface UserService {

    VoidResponse create(User user);

    User getUser(Long userId);

    boolean isEmpty(User user);

}
