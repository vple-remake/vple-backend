package com.app.vple.domain.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RestaurantReviewModifyDto {

    private Long recommandRestaurantId;

    @NotBlank(message = "가장 마음에 드는 평가를 골라주세요")
    private String evaluate;

    @NotBlank(message = "제목이 필요합니다.")
    private String title;

    @NotNull(message = "내용이 필요합니다.")
    private String text;

    @NotNull(message = "이미지가 필요합니다.")
    private String image;

}
