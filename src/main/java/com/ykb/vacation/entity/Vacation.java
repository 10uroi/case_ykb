package com.ykb.vacation.entity;

import com.ykb.vacation.enums.ApprovalType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date startDate;
    private Date endDate;

    private long dayCount;

    private ApprovalType approval = ApprovalType.WAITING;

}
