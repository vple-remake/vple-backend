package com.app.vple.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "evaluation")
@Getter
@Builder
@AllArgsConstructor
public class Evaluation {
    public Evaluation(){}

    public Evaluation(RecommandRestaurant recommandRestaurant) {
        this.recommandRestaurant = recommandRestaurant;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "recommand_restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RecommandRestaurant recommandRestaurant;

    private Integer delicious;

    private Integer fresh;

    private Integer kind;

    @PrePersist
    public void prePersist () {
        this.delicious = this.delicious == null ? 0 : this.delicious;
        this.fresh = this.fresh == null ? 0 : this.fresh;
        this.kind = this.kind == null ? 0 : this.kind;
    }

    public void addEvaluaion(String evaluate) {
        if (evaluate.equals("음식이 맛있어요")) {
            delicious += 1;
        }
        else if (evaluate.equals("재료가 신선해요")) {
            fresh += 1;
        }
        else if (evaluate.equals("직원이 친절해요")) {
            kind += 1;
        }
        else {
            delicious += 1;
        }
    }

    public void reduceEvaluation(String evaluate) {
        if (evaluate == "음식이 맛있어요") {
            delicious -= 1;
        }
        else if (evaluate == "재료가 신선해요") {
            fresh -= 1;
        }
        else if (evaluate == "직원이 친절해요") {
            kind -= 1;
        }
        else {
            delicious -= 1;
        }
    }

}
