package com.app.vple.domain.dto;

import com.app.vple.domain.Plan;
import com.app.vple.domain.PlanTravel;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MyPlansDto {

    private Long id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private String image1;

    private String image2;

    public String imageEmpty(List<PlanTravel> planTravels, int idx) {
        if (planTravels.size() <= idx) {
            return null;
        }
        else {
            if (planTravels.get(idx).getImage() == null) {
                return null;
            }
            else {
                return planTravels.get(idx).getImage();
            }
        }
    }

    public MyPlansDto(Plan entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.image1 = imageEmpty(entity.getPlanTravels(), 0);
        this.image2 = imageEmpty(entity.getPlanTravels(), 1);
    }
}
