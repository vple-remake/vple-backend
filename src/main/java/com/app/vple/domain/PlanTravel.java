package com.app.vple.domain;

import com.app.vple.domain.dto.PlanTravelUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "plan_travel")
@Getter
@Builder
@AllArgsConstructor
public class PlanTravel {

    public PlanTravel() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_travel_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Plan plan;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String latitude;

    private String image;

    @Column(nullable = false)
    private int day;

    @Column(nullable = false)
    private LocalTime startTime;

    public void updatePlanTravel(PlanTravelUpdateDto planTravelUpdateDto) {
        this.startTime = planTravelUpdateDto.getStartTime();
    }
}
