package com.ykb.vacation.service.imp;

import com.ykb.vacation.dto.VacationRequest;
import com.ykb.vacation.dto.VoidResponse;
import com.ykb.vacation.entity.User;
import com.ykb.vacation.entity.Vacation;
import com.ykb.vacation.entity.VacationConfig;
import com.ykb.vacation.enums.ApprovalType;
import com.ykb.vacation.repository.VacationRepository;
import com.ykb.vacation.service.UserDetailService;
import com.ykb.vacation.service.UserService;
import com.ykb.vacation.service.VacationConfigService;
import com.ykb.vacation.service.VacationService;
import com.ykb.vacation.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VacationServiceImp implements VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private VacationConfigService vacationConfigService;

    /**
     * İzin oluşturulmak için kullanmaktayiz.
     *
     * @param vacationRequest
     * @return
     */
    public VoidResponse create(VacationRequest vacationRequest) {
        return create(vacationRequest.getUser(), vacationRequest.getStartDate(), vacationRequest.getEndDate());
    }

    /**
     * İzin oluşturulmak için kullanmaktayiz.
     *
     * @param user
     * @param startDate
     * @param endDate
     * @return
     */
    public VoidResponse create(User user, Date startDate, Date endDate) {
        if (user == null || user.getId() == null)
            return new VoidResponse(true, "user.not.found");

        User mUser = userService.getUser(user.getId());
        if (mUser == null) return new VoidResponse(true, "user.not.found.db");

        VoidResponse dateControl = DateFormat.dateControl(startDate, endDate);
        if (dateControl.isError()) return dateControl;

        VoidResponse vacationControl = vacationControl(mUser, startDate, endDate);
        if (vacationControl.isError()) return vacationControl;

        if (insert(mUser, startDate, endDate).getId() > 0)
            return new VoidResponse(false, "vac.success");
        else
            return new VoidResponse(false, "vac.error.db");
    }

    @Override
    public VoidResponse approval(Vacation vacation, ApprovalType approvalType) {
        if (vacation == null || vacation.getId() == null)
            return new VoidResponse(true, "vac.not.found");

        Vacation mVacation = vacationRepository.findById(vacation.getId()).orElse(null);
        if (mVacation == null) return new VoidResponse(true, "vac.not.found");

        if (mVacation.getApproval().equals(ApprovalType.WAITING) && !approvalType.equals(ApprovalType.WAITING)) {
            mVacation.setApproval(approvalType);
            vacationRepository.save(mVacation);
            return new VoidResponse(false, "vac.change.approval");
        }
        return new VoidResponse(true, "vac.not.waiting");
    }

    /**
     * İzni veritabanına kayıt ediyoruz.
     *
     * @param user
     * @param startDate
     * @param endDate
     * @return
     */
    private Vacation insert(User user, Date startDate, Date endDate) {
        Vacation vacation = new Vacation();
        vacation.setDayCount(DateFormat.dateBetweenCount(startDate, endDate));
        vacation.setStartDate(startDate);
        vacation.setEndDate(endDate);
        vacation.setUser(user);
        return vacationRepository.save(vacation);
    }

    /**
     * İzin ile ilgili kontroller yapılıyor.
     *
     * @param user
     * @param startDate
     * @param endDate
     * @return
     */
    private VoidResponse vacationControl(User user, Date startDate, Date endDate) {

        //Aynı tarih içinde daha önce izin alındı mı?
        VoidResponse voidResponse = vacationDuplicate(user, startDate, endDate);
        if (voidResponse.isError()) return voidResponse;

        //Yıl içinde kullandığı izin sayısı
        Double count = vacationRepository.getByDayCountStartDateAndEndDateAndUser_Id(DateFormat.getYearFirst(), DateFormat.getYearLast(), user.getId());
        if (count == null) count = 0d;

        //Kaç yıllık çalışan olduğu ve hangi skalada olduğu
        long workYear = userDetailService.getWorkYear(user);
        VacationConfig vacationConfig = vacationConfigService.getVacationConfig(workYear);

        //Kullanmak(talep edilen) istenen izin sayısı
        long requestUseCount = DateFormat.dateBetweenCount(startDate, endDate);

        //İzin hakkı istenen izin süresi kadar mı?
        VoidResponse vacationNotYetWon = vacationNotYetWon(requestUseCount, vacationConfig);
        if (vacationNotYetWon.isError()) return vacationNotYetWon;

        //İzin hakkı bitti mi?
        VoidResponse vacationNotEnough = vacationNotEnough(count, vacationConfig);
        if (vacationNotEnough.isError()) return vacationNotEnough;

        //Kullanmak istediği izin ile kullandığı izinlerin toplamı, hakkından fazla mı?
        VoidResponse vacationNotEnoughVac = vacationNotEnoughVac(count, vacationConfig, requestUseCount);
        if (vacationNotEnoughVac.isError()) return vacationNotEnoughVac;

        return new VoidResponse(false, "vac.no.problem");
    }

    /**
     * Aynı tarih içinde daha önce izin alındı mı?
     *
     * @param user
     * @param startDate
     * @param endDate
     * @return
     */
    public VoidResponse vacationDuplicate(User user, Date startDate, Date endDate) {
        if (userDetailService.isVacationDuplicate(user, startDate, endDate))
            return new VoidResponse(true, "already.between.dates");
        return new VoidResponse(false);
    }

    /**
     * İzin hakkı istenen izin süresi kadar mı?
     *
     * @param requestUseCount
     * @param vacationConfig
     * @return
     */
    private VoidResponse vacationNotYetWon(long requestUseCount, VacationConfig vacationConfig) {
        if (requestUseCount > vacationConfig.getDayCount()) {
            return new VoidResponse(true, "vac.not.yet.won");
        }
        return new VoidResponse(false);
    }

    /**
     * İzin hakkı bitti mi?
     *
     * @param count
     * @param vacationConfig
     * @return
     */
    private VoidResponse vacationNotEnough(Double count, VacationConfig vacationConfig) {
        if (vacationConfig.getDayCount() < count) {
            return new VoidResponse(true, "vac.not.enough");
        }
        return new VoidResponse(false);
    }

    /**
     * Kullanmak istediği izin ile kullandığı izinlerin toplamı, hakkından fazla mı?
     *
     * @param count
     * @param vacationConfig
     * @param requestUseCount
     * @return
     */
    private VoidResponse vacationNotEnoughVac(Double count, VacationConfig vacationConfig, long requestUseCount) {
        if (vacationConfig.getDayCount() < count + requestUseCount) {
            return new VoidResponse(true, "vac.not.eno");
        }
        return new VoidResponse(false);
    }
}
