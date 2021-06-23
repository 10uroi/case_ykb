package com.ykb.vacation;

import com.ykb.vacation.dto.VacationApprovalRequest;
import com.ykb.vacation.dto.VacationRequest;
import com.ykb.vacation.entity.User;
import com.ykb.vacation.entity.Vacation;
import com.ykb.vacation.enums.ApprovalType;
import com.ykb.vacation.util.Converter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class ApiRequestTest_0_1 {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    private Long day = (1000 * 60 * 60 * 24l);

    @Test
    public void createUser() throws Exception {
        User user = new User();
        user.setId(1l);
        user.setName("Onur");
        user.setSurname("Ciner");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/user/create")
                .content(Converter.convert(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(mvcResult -> {
                    log.info("Request  : " + mvcResult.getRequest().getPathInfo() + "?" + mvcResult.getRequest().getQueryString());
                    log.info("Response : " + mvcResult.getResponse().getContentAsString());
                });
    }

    @Test
    public void createVacationTwoDay() throws Exception {
        User user = new User();
        user.setId(1l);

        VacationRequest vacationRequest = new VacationRequest();

        vacationRequest.setUser(user);
        vacationRequest.setStartDate(new Date(new Date().getTime() + day));
        vacationRequest.setEndDate(new Date(new Date().getTime() + (day * 2)));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/vacation/create?lang=tr")
                .content(Converter.convert(vacationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(mvcResult -> {
                    log.info("Request  : " + mvcResult.getRequest().getPathInfo() + "?" + mvcResult.getRequest().getQueryString());
                    log.info("Response : " + mvcResult.getResponse().getContentAsString());
                });
    }

    @Test
    public void createVacationThreeDay() throws Exception {
        User user = new User();
        user.setId(1l);

        VacationRequest vacationRequest = new VacationRequest();

        vacationRequest.setUser(user);
        vacationRequest.setStartDate(new Date(new Date().getTime() + day * 5));
        vacationRequest.setEndDate(new Date(new Date().getTime() + (day * 8)));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/vacation/create")
                .content(Converter.convert(vacationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(mvcResult -> {
                    log.info("Request  : " + mvcResult.getRequest().getPathInfo() + "?" + mvcResult.getRequest().getQueryString());
                    log.info("Response : " + mvcResult.getResponse().getContentAsString());
                });
    }

    @Test
    public void createVacationOneDay() throws Exception {
        User user = new User();
        user.setId(1l);

        VacationRequest vacationRequest = new VacationRequest();

        vacationRequest.setUser(user);
        vacationRequest.setStartDate(new Date(new Date().getTime() + day * 10));
        vacationRequest.setEndDate(new Date(new Date().getTime() + (day * 11)));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/vacation/create")
                .content(Converter.convert(vacationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(mvcResult -> {
                    log.info("Request  : " + mvcResult.getRequest().getPathInfo() + "?" + mvcResult.getRequest().getQueryString());
                    log.info("Response : " + mvcResult.getResponse().getContentAsString());
                });
    }

    @Test
    public void approvalVacation() throws Exception {
        Vacation vacation = new Vacation();
        vacation.setId(1l);

        VacationApprovalRequest vacationRequest = new VacationApprovalRequest();

        vacationRequest.setVacation(vacation);
        vacationRequest.setApprovalType(ApprovalType.ACCEPT);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/vacation/approval")
                .content(Converter.convert(vacationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(mvcResult -> {
                    log.info("Request  : " + mvcResult.getRequest().getPathInfo() + "?" + mvcResult.getRequest().getQueryString());
                    log.info("Response : " + mvcResult.getResponse().getContentAsString());
                });
    }
}
