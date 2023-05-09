package com.app.vple.domain;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "check_duplicated_plan_likes")
public class CheckDuplicatedPlanLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Plan plan;

    public CheckDuplicatedPlanLike(User user, Plan plan) {
        this.user = user;
        this.plan = plan;
    }
}
