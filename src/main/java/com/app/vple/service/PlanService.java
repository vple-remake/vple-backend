package com.app.vple.service;

import com.app.vple.domain.CheckDuplicatedPlanLike;
import com.app.vple.domain.Plan;
import com.app.vple.domain.User;
import com.app.vple.domain.dto.*;
import com.app.vple.repository.CheckDuplicatedPlanLikeRepository;
import com.app.vple.repository.PlanRepository;
import com.app.vple.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService {

    private final PlanRepository planRepository;

    private final UserRepository userRepository;

    private final CheckDuplicatedPlanLikeRepository checkDuplicatedPlanLikeRepository;

    public List<MyPlansDto> findPlan(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException("해당 사용자가 존재하지 않습니다.")
        );

        List<Plan> planByUser = planRepository.findPlanByUser(user);

        return planByUser.stream().map(
                MyPlansDto::new
        ).collect(Collectors.toList());
    }

    public Page<PlanListDto> findLikeList(Pageable pageable) {
        Page<Plan> plans = planRepository.findPlanByIsOpenedTrue(pageable);

        return plans.map(PlanListDto::new);
    }

    public PlanDetailDto findPlanDetails(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(
                () -> new NoSuchElementException("해당 플랜이 존재하지 않습니다.")
        );

        return new PlanDetailDto(plan);
    }

    public Long addPlan(PlanCreateDto planCreateDto, String email) {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new NoSuchElementException("해당 사용자가 존재하지 않습니다."));
            Plan plan = planRepository.save(planCreateDto.toEntity(user));
            return plan.getId();
        } catch (Exception e) {
            throw new IllegalStateException("형식이 잘못되었습니다.");
        }
    }

    public String removePlan(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 플랜이 존재하지 않습니다.")
        );

        planRepository.delete(plan);

        return plan.getTitle();
    }

    public String modifyPlan(Long id, PlanUpdateDto planUpdateDto) {
        Plan plan = planRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 플랜이 존재하지 않습니다.")
        );

        plan.updatePlan(planUpdateDto);
        planRepository.save(plan);

        return plan.getTitle();
    }

    public String changePlanLike(Long id, String email) {
        Plan plan = planRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 플랜이 존재하지 않습니다.")
        );

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException("해당 사용자가 존재하지 않습니다.")
        );

        CheckDuplicatedPlanLike planLike = checkDuplicatedPlanLikeRepository.findByUserAndPlan(user, plan)
                .orElse(null);

        if (planLike == null) {
            checkDuplicatedPlanLikeRepository.save(new CheckDuplicatedPlanLike(user, plan));
            return plan.getTitle() + "좋아요 + 1";
        }
        else {
            checkDuplicatedPlanLikeRepository.delete(planLike);
            return plan.getTitle() + "좋아요 취소";
        }

    }
}
