package com.app.vple.domain.dto;

import com.app.vple.domain.RestaurantReview;
import com.app.vple.domain.User;
import lombok.Data;

@Data
public class RestaurantReviewDto {

    private Long id;

    private User user;

    private String title;

    private String text;

    private String image;


    public RestaurantReviewDto(RestaurantReview entity) {
        id = entity.getId();
        user = entity.getUser();
        title = entity.getTitle();
        text = entity.getText();
        image = entity.getImage();
    }
}