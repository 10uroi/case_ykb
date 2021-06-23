package com.ykb.vacation.repository;

import com.ykb.vacation.entity.Vacation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VacationRepository extends CrudRepository<Vacation, Long> {

    @Query("select SUM(v.dayCount) FROM  Vacation v WHERE v.startDate between :startDate and :endDate and v.user.id = :userId")
    Double getByDayCountStartDateAndEndDateAndUser_Id(Date startDate, Date endDate, Long userId);

    @Query("select SUM(v.dayCount) FROM  Vacation v WHERE ((v.startDate between :startDate and :endDate) or (v.endDate between :startDate and :endDate)) and v.user.id = :userId")
    Double getByDayCountStartDateAndEndDateAndUser(Date startDate, Date endDate, Long userId);

}
