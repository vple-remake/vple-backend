package com.app.vple.controller;

import com.app.vple.domain.User;
import com.app.vple.domain.dto.RestaurantReviewAddDto;
import com.app.vple.domain.dto.RestaurantReviewListDto;
import com.app.vple.domain.dto.RestaurantReviewModifyDto;
import com.app.vple.service.RestaurantReviewService;
import com.app.vple.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class RestaurantReviewController {

    private final UserService userService;

    private final RestaurantReviewService restaurantReviewService;


    @ApiOperation(value = "식당 후기 추가하기")
    @PostMapping("/auth/restaurant_review")
    public ResponseEntity<?> restaurantReviewAdd(HttpServletRequest request, @Validated @RequestBody RestaurantReviewAddDto restaurantReviewAddDto) {
        try {
            User loginUser = userService.getUser(request);
            String restaurantReview = restaurantReviewService.addRestaurantReview(restaurantReviewAddDto, loginUser);

            return new ResponseEntity<>(restaurantReview + "등록완료", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @ApiOperation(value = "식당 후기 모아보기")
    @GetMapping("/api/restaurant_review/{id}")
    public ResponseEntity<?> restaurantReviewList(@PathVariable Long id, @PageableDefault(size=5)Pageable pageable) {
        try {
            Page<RestaurantReviewListDto> restaurantReviews = restaurantReviewService.findRestaurantReviewList(id, pageable);
            return new ResponseEntity<>(restaurantReviews,HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @ApiOperation(value = "식당 후기 수정")
    @PatchMapping("auth/restaurant_review/{id}")
    public ResponseEntity<?> restaurantReviewModify(@PathVariable Long id, @Validated @RequestBody RestaurantReviewModifyDto restaurantReviewModifyDto) {
        try {
            String restaurantReview = restaurantReviewService.modifyRestaurantReview(id, restaurantReviewModifyDto);

            return new ResponseEntity<>(restaurantReview + "수정완료", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "식당 후기 삭제")
    @DeleteMapping("auth/restaurant_review/{id}")
    public ResponseEntity<?> restaurantReviewDelete(@PathVariable Long id) {
        try {
            String title = restaurantReviewService.removeRestaurantReview(id);

            return new ResponseEntity<>(title + "을 삭제했습니다.", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
