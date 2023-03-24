package com.app.vple.domain;

import com.app.vple.domain.dto.RestaurantReviewModifyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class RestaurantReview {

    public RestaurantReview() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommand_restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RecommandRestaurant recommandRestaurant;

    @Column(nullable = false)
    private String evaluate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDate createDate;

    @Column(nullable = false)
    private LocalDate modifiedDate;

    private String image;


    public void updateRestaurantReview(RestaurantReviewModifyDto restaurantReviewModifyDto) {
        this.evaluate = restaurantReviewModifyDto.getEvaluate();
        this.title = restaurantReviewModifyDto.getTitle();
        this.text = restaurantReviewModifyDto.getText();
        this.image = restaurantReviewModifyDto.getImage();
    }


}
