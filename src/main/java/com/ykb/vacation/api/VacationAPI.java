package com.ykb.vacation.api;

import com.ykb.vacation.dto.VacationRequest;
import com.ykb.vacation.dto.VoidResponse;
import com.ykb.vacation.language.LanguageFactory;
import com.ykb.vacation.service.imp.VacationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/vacation")
public class VacationAPI {

    @Autowired
    private VacationServiceImp vacationServiceImp;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<VoidResponse> create(@RequestBody VacationRequest vacationRequest, @RequestParam(required = false) String lang) {
        VoidResponse voidResponse = vacationServiceImp.create(vacationRequest);
        return ResponseEntity.ok(LanguageFactory.convert(voidResponse, lang));
    }

}
