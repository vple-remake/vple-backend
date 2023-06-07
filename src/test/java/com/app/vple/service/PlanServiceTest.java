package com.app.vple.service;

import com.app.vple.domain.Plan;
import com.app.vple.domain.dto.PlanCreateDto;
import com.app.vple.domain.dto.PlanDetailDto;
import com.app.vple.repository.PlanRepository;
import com.app.vple.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlanServiceTest {

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    private Plan plan;

    private Long planId;

    @Test
    void 플랜_생성_테스트() {
        PlanCreateDto planCreateDto = new PlanCreateDto();

        planCreateDto.setTitle("강릉 여행");
        planCreateDto.setCity("강릉");
        planCreateDto.setDistrict("속초");
        planCreateDto.setStartDate(LocalDate.of(2023,07,01));
        planCreateDto.setEndDate(LocalDate.of(2023,07,07));
        planCreateDto.setPeopleNum(4);

        planId = planService.addPlan(planCreateDto, "kimsh2948@kakao.com");
        plan = planRepository.findById(planId).get();

        assertEquals(planCreateDto.getTitle(), plan.getTitle());
    }

    @Test
    void 플랜_삭제_테스트() {

    }
}