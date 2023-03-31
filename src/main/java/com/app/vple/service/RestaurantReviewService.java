package com.app.vple.service;


import com.app.vple.domain.Evaluation;
import com.app.vple.domain.RecommandRestaurant;
import com.app.vple.domain.RestaurantReview;
import com.app.vple.domain.User;
import com.app.vple.domain.dto.RestaurantReviewAddDto;
import com.app.vple.domain.dto.RestaurantReviewListDto;
import com.app.vple.domain.dto.RestaurantReviewModifyDto;
import com.app.vple.repository.EvaluationRepository;
import com.app.vple.repository.RecommandRestaurantRepository;
import com.app.vple.repository.RestaurantReviewRepository;
import com.app.vple.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantReviewService {

    private final RestaurantReviewRepository restaurantReviewRepository;

    private final RecommandRestaurantRepository recommandRestaurantRepository;

    private final EvaluationRepository evaluationRepository;

    private final UserRepository userRepository;


    public String addRestaurantReview(RestaurantReviewAddDto restaurantReviewAddDto, User loginUser) {
        Long recommandRestaurantId = restaurantReviewAddDto.getRecommandRestaurantId();
        String userEmail = loginUser.getEmail();

        RecommandRestaurant recommandRestaurant = recommandRestaurantRepository.findById(recommandRestaurantId).orElseThrow(
                () -> new NoSuchElementException("해당 식당이 없습니다.")
        );

        System.out.println("식당 체크 완료!!!");

        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new NoSuchElementException("해당 사용자가 존재하지 않습니다.")
        );

        restaurantReviewRepository.save(restaurantReviewAddDto.toEntity(recommandRestaurant, user));

        Evaluation evaluation = evaluationRepository.findByRecommandRestaurant(recommandRestaurant);
        if(evaluation == null) {
            evaluation = new Evaluation(recommandRestaurant);
            evaluationRepository.save(evaluation);
        }

        evaluation.addEvaluaion(restaurantReviewAddDto.getEvaluate());

        evaluationRepository.save(evaluation);

        return restaurantReviewAddDto.getTitle();
    }

    public Page<RestaurantReviewListDto> findRestaurantReviewList(Long id, Pageable pageable) {
        Page<RestaurantReview> restaurantReviews = restaurantReviewRepository.findAll(pageable);

        return restaurantReviews.map(RestaurantReviewListDto::new);
    }

    public String modifyRestaurantReview(Long id, RestaurantReviewModifyDto restaurantReviewModifyDto){


        RestaurantReview restaurantReview = restaurantReviewRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 식당 리뷰가 존재하지 않습니다.")
        );

        RecommandRestaurant recommandRestaurant = recommandRestaurantRepository.findById(restaurantReviewModifyDto.getRecommandRestaurantId()).orElseThrow(
                () -> new NoSuchElementException("해당 식당이 없습니다.")
        );

        Evaluation evaluation = evaluationRepository.findByRecommandRestaurant(recommandRestaurant);


        if (restaurantReview.getEvaluate().equals(restaurantReviewModifyDto.getEvaluate())) {

            restaurantReview.updateRestaurantReview(restaurantReviewModifyDto);

        }
        else {

            evaluation.reduceEvaluation(restaurantReview.getEvaluate());
            restaurantReview.updateRestaurantReview(restaurantReviewModifyDto);
            evaluation.addEvaluaion(restaurantReview.getEvaluate());

            evaluationRepository.save(evaluation);
        }

        restaurantReviewRepository.save(restaurantReview);

        return restaurantReviewModifyDto.getTitle();
    }

    public String removeRestaurantReview(Long id) {
        RestaurantReview restaurantReview = restaurantReviewRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 식당리뷰가 존재하지 않습니다.")
        );

        Long recommandRestaurantId = restaurantReview.getRecommandRestaurant().getId();

        RecommandRestaurant recommandRestaurant = recommandRestaurantRepository.findById(recommandRestaurantId).orElseThrow(
                () -> new NoSuchElementException("해당 식당이 존재하지 않습니다.")
        );

        Evaluation evaluation = evaluationRepository.findByRecommandRestaurant(recommandRestaurant);

        evaluation.reduceEvaluation(restaurantReview.getEvaluate());

        restaurantReviewRepository.delete(restaurantReview);

        return restaurantReview.getTitle();
    }
}
