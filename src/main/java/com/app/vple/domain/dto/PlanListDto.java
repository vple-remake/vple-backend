package com.app.vple.domain.dto;

import com.app.vple.domain.Plan;
import com.app.vple.domain.PlanTravel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanListDto {

    private Long id;

    private String title;

    private String nickname;

    private Integer likeCount;

    private LocalDate startDate;

    private LocalDate endDate;

    private String image;

    public PlanListDto(Plan entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.nickname = entity.getUser().getNickname();
        this.likeCount = entity.getLikesCount();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.image = entity.getPlanTravels().stream()
                .filter(planTravel -> planTravel != null && planTravel.getImage() != null)
                .map(PlanTravel::getImage)
                .findFirst()
                .orElse(null);
    }
}
