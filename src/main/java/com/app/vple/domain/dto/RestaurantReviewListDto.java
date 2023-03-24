package com.app.vple.domain.dto;

import com.app.vple.domain.RestaurantReview;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RestaurantReviewListDto {

    private Long id;

    private String title;

    private String text;

    private String image;

    private LocalDate createDate;

    private LocalDate modifiedDate;

    public RestaurantReviewListDto(RestaurantReview entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.image = entity.getImage();
        this.createDate = entity.getCreateDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
