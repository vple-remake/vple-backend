package com.app.vple.controller;

import com.app.vple.domain.Plan;
import com.app.vple.domain.User;
import com.app.vple.domain.dto.*;
import com.app.vple.service.PlanService;
import com.app.vple.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final UserService userService;

    private final PlanService planService;

    @GetMapping("/auth/plan")
    public ResponseEntity<?> planList(HttpServletRequest request) {
        try {
            User loginUser = userService.getUser(request);
            List<MyPlansDto> plans = planService.findPlan(loginUser.getEmail());
            return new ResponseEntity<>(plans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/plan/like")
    public ResponseEntity<?> planLikeList(
            @PageableDefault(size = 20, sort="likesCount", direction = Sort.Direction.DESC) Pageable pageable) {
        try {
            Page<PlanListDto> plans = planService.findLikeList(pageable);
            return new ResponseEntity<>(plans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/plan/{id}")
    public ResponseEntity<?> planDetails(@PathVariable Long id) {
        try {
            PlanDetailDto plan = planService.findPlanDetails(id);
            return new ResponseEntity<>(plan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auth/liked/plan")
    public ResponseEntity<?> likedPlans(HttpServletRequest request) {
        try {
            User loginUser = userService.getUser(request);
            List<MyPlansDto> plans = planService.findLikedPlans(loginUser.getEmail());
            return new ResponseEntity<>(plans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/plan")
    public ResponseEntity<?> planAdd(HttpServletRequest request, @Validated @RequestBody PlanCreateDto planCreateDto) {
        try {
            User loginUser = userService.getUser(request);
            String email = loginUser.getEmail();
            Long id = planService.addPlan(planCreateDto, email);
            return new ResponseEntity<>(id + " 추가완료.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/auth/plan/{id}")
    public ResponseEntity<?> planRemove(@PathVariable Long id) {
        try {
            String title = planService.removePlan(id);
            return new ResponseEntity<>(title + "을 삭제했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/auth/plan/{id}")
    public ResponseEntity<?> planModify(@PathVariable Long id,
                                        @Validated @RequestBody PlanUpdateDto planUpdateDto) {
        try {
            String title = planService.modifyPlan(id, planUpdateDto);
            return new ResponseEntity<>(title + "을 수정했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/auth/plan/like/{id}")
    public ResponseEntity<?> planLikeAdd(HttpServletRequest request, @PathVariable Long id) {
        try {
            User loginUser = userService.getUser(request);
            String result = planService.changePlanLike(id, loginUser.getEmail());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
