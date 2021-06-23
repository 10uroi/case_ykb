package com.ykb.vacation.service.imp;

import com.ykb.vacation.dto.VoidResponse;
import com.ykb.vacation.entity.User;
import com.ykb.vacation.repository.UserRepository;
import com.ykb.vacation.repository.VacationRepository;
import com.ykb.vacation.service.UserService;
import com.ykb.vacation.service.UserDetailService;
import com.ykb.vacation.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserServiceImp implements UserService, UserDetailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Override
    @Transactional
    public VoidResponse create(User user) {
        if (isEmpty(user)) return new VoidResponse(true, "user.empty");
        return userRepository.save(user).getId() > 0 ? new VoidResponse(false, "user.reg.success") : new VoidResponse(true, "user.err.db");
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean isEmpty(User user) {
        if (user == null) return true;
        if (user.getName() == null || user.getSurname() == null) return true;
        return false;
    }

    @Override
    public boolean isVacationDuplicate(User user, Date startDate, Date endDate) {
        Double duplicate = vacationRepository.getByDayCountStartDateAndEndDateAndUser(startDate, endDate, user.getId());
        if (duplicate == null) return false;
        return duplicate > 0 ? true : false;
    }

    @Override
    public long getWorkYear(User user) {
        user = getUser(user.getId());
        return DateFormat.dateBetweenYearCount(user.getStartWorkDate(), new Date());
    }
}
