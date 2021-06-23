package com.ykb.vacation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VacationConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int startYear;
    private int endYear;
    private int dayCount;

    public VacationConfig(int startYear, int endYear, int dayCount) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.dayCount = dayCount;
    }
}
