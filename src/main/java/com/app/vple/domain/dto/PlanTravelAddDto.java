package com.app.vple.domain.dto;

import com.app.vple.domain.Plan;
import com.app.vple.domain.PlanTravel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
public class PlanTravelAddDto {

    @NotBlank(message = "장소 이름이 필요합니다.")
    private String name;

    private Long planId;

    @NotNull(message = "경도가 필요합니다.")
    private String longitude;

    @NotNull(message = "위도가 필요합니다.")
    private String latitude;

    private String image;

    @NotNull(message = "몇일째의 여행지인지가 필요합니다.")
    private int day;

    @NotNull(message = "해당 장소의 여행 시작 시간을 적어주세요.")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime startTime;

    public PlanTravel toEntity(Plan plan) {
        return PlanTravel.builder()
                .name(name)
                .longitude(longitude)
                .latitude(latitude)
                .image(image)
                .day(day)
                .startTime(startTime)
                .plan(plan)
                .build();
    }
}
