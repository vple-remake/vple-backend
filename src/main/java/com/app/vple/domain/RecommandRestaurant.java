package com.app.vple.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="recommand_restaurant")
@Getter
public class RecommandRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommand_restaurant_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String category;

    private String introduction;

    private String phoneNumber;

    private String openTime;

    private Float rating;

    private String latitude;

    private String longitude;

    private String address;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String city;

    private String image;

    @OneToOne(mappedBy = "recommandRestaurant")
    private Evaluation evaluation;

    @OneToMany(mappedBy = "recommandRestaurant")
    private List<Menu> menus;

    @OneToMany(mappedBy = "recommandRestaurant")
    private List<RestaurantReview> restaurantReviews;

}
