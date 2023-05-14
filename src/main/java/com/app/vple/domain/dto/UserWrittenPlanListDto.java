package com.app.vple.domain.dto;

import com.app.vple.domain.Plan;
import com.app.vple.domain.PlanTravel;
import lombok.Data;

@Data
public class UserWrittenPlanListDto {

    private Long id;

    private String title;

    private String image;

    public UserWrittenPlanListDto(Plan entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.image = entity.getPlanTravels().stream()
                .findFirst() // 첫 번째 요소만 선택
                .map(PlanTravel::getImage) // 이미지 필드를 매핑
                .orElse(null); // 값이 없을 경우에는 null 반환
    }
}
