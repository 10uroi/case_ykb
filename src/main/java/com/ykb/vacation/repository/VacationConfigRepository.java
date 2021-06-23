package com.ykb.vacation.repository;

import com.ykb.vacation.entity.VacationConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationConfigRepository extends CrudRepository<VacationConfig, Long> {

    @Query("select v from VacationConfig v WHERE v.startYear<=:workYear AND v.endYear>=:workYear")
    VacationConfig getByStartYearBetweenAndEndYear(int workYear);

}
