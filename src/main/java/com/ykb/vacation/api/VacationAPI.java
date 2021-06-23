package com.ykb.vacation.api;

import com.ykb.vacation.dto.VacationApprovalRequest;
import com.ykb.vacation.dto.VacationRequest;
import com.ykb.vacation.dto.VoidResponse;
import com.ykb.vacation.entity.Vacation;
import com.ykb.vacation.language.LanguageFactory;
import com.ykb.vacation.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/vacation")
public class VacationAPI {

    @Autowired
    private VacationService vacationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<VoidResponse> create(@RequestBody VacationRequest vacationRequest, @RequestParam(required = false) String lang) {
        VoidResponse voidResponse = vacationService.create(vacationRequest);
        return ResponseEntity.ok(LanguageFactory.convert(voidResponse, lang));
    }

    @RequestMapping(value = "/approval", method = RequestMethod.POST)
    public ResponseEntity<VoidResponse> approval(@RequestBody VacationApprovalRequest vacationApprovalRequest, @RequestParam(required = false) String lang) {
        VoidResponse voidResponse = vacationService.approval(vacationApprovalRequest.getVacation(),vacationApprovalRequest.getApprovalType());
        return ResponseEntity.ok(LanguageFactory.convert(voidResponse, lang));
    }
}
