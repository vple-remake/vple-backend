package com.app.vple.domain.dto;

import com.app.vple.domain.RestaurantReview;
import com.app.vple.domain.User;
import lombok.Data;

@Data
public class RestaurantReviewDto {

    private Long id;

    private String nickname;

    private String title;

    private String text;

    private String image;


    public RestaurantReviewDto(RestaurantReview entity) {
        this.id = entity.getId();
        this.nickname = entity.getUser().getNickname();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.image = entity.getImage();
    }
}