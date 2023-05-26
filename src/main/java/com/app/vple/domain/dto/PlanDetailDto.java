package com.app.vple.domain.dto;

import com.app.vple.domain.Plan;
import com.app.vple.domain.PlanTravel;
import lombok.Data;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlanDetailDto {

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private String district;

    private String city;

    private List<PlanTravelDto> planTravels;

    private boolean isOpened;

    private Integer likesCount;

    private Integer totalDays;

    public PlanDetailDto(Plan entity) {
        this.title = entity.getTitle();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.district= entity.getDistrict();
        this.city = entity.getCity();
        this.planTravels = entity.getPlanTravels()
                .stream()
                .sorted(Comparator.comparing(PlanTravel::getDay)
                .thenComparing(PlanTravel::getStartTime))
                .map(PlanTravelDto::new)
                .collect(Collectors.toList());
        this.isOpened = entity.isOpened();
        this.likesCount = entity.getLikesCount();
        this.totalDays = entity.getEndDate().getDayOfMonth() - entity.getStartDate().getDayOfMonth() + 1;
    }
}
