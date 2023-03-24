package com.app.vple.domain.dto;

import com.app.vple.domain.RecommandRestaurant;
import com.app.vple.domain.RestaurantReview;
import com.app.vple.domain.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class RestaurantReviewAddDto {

    private Long recommandRestaurantId;

    @NotBlank(message = "제목이 필요합니다.")
    private String title;

    @NotNull(message = "내용이 필요합니다.")
    private String text;

    @NotNull(message = "이미지가 필요합니다.")
    private String image;

    @NotNull(message = "식당의 평가를 골라주세요.")
    private String evaluate;

    public RestaurantReview toEntity(RecommandRestaurant recommandRestaurant, User user) {

        return RestaurantReview.builder()
                .evaluate(evaluate)
                .title(title)
                .text(text)
                .createDate(LocalDate.now())
                .modifiedDate(LocalDate.now())
                .image(image)
                .recommandRestaurant(recommandRestaurant)
                .user(user)
                .build();
    }
}
