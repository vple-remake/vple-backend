package com.app.vple.domain.dto;

import com.app.vple.domain.PlanTravel;
import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
public class PlanTravelDto {

    private Long id;

    private String name;

    private int day;

    private String startTime;

    private String Image;

    public PlanTravelDto(PlanTravel entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.day = entity.getDay();
        this.startTime = entity.getStartTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        this.Image = entity.getImage();
    }
}
