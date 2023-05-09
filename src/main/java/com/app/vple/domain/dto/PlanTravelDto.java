package com.app.vple.domain.dto;

import com.app.vple.domain.PlanTravel;
import lombok.Data;

import java.time.LocalTime;

@Data
public class PlanTravelDto {

    private Long id;

    private String name;

    private int day;

    private LocalTime startTime;

    private String Image;

    public PlanTravelDto(PlanTravel entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.day = entity.getDay();
        this.startTime = entity.getStartTime();
        this.Image = entity.getImage();
    }
}
