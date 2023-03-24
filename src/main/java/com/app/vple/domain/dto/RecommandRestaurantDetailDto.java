package com.app.vple.domain.dto;

import com.app.vple.domain.Evaluation;
import com.app.vple.domain.RecommandRestaurant;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RecommandRestaurantDetailDto {

    private Long id;

    private String name;

    private String category;

    private String introduction;

    private String phoneNumber;

    private String openTime;

    private String address;

    private String district;

    private String city;

    private String latitude;

    private String longitude;

    private List<MenuDto> menus;

    private List<RestaurantReviewDto> restaurantReviews;

    private HashMap<String,Integer> evaluation;

    private float rating;

    private String image;

    private List<String> veganTypes = new ArrayList<String>();

    public List<String> veganTypeList(List<String> veganTypes) {
        for (int i=0; i < this.menus.size(); i++) {
            String veganType = this.menus.get(i).getVeganType();

            if (!veganTypes.contains(veganType))
                veganTypes.add(veganType);
        }
        return veganTypes;
    }

    public HashMap<String, Integer> statisticsOfEvaluation(Evaluation evaluation) {
        HashMap<String,Integer> statisticsMap = new HashMap<String, Integer>();
        statisticsMap.put("delicious", evaluation.getDelicious());
        statisticsMap.put("fresh", evaluation.getFresh());
        statisticsMap.put("kind", evaluation.getKind());

        return statisticsMap;
    }

    public RecommandRestaurantDetailDto(RecommandRestaurant entity) {

        this.id = entity.getId();
        this.name = entity.getName();
        this.category = entity.getCategory();
        this.introduction = entity.getIntroduction();
        this.phoneNumber = entity.getPhoneNumber();
        this.openTime = entity.getOpenTime();
        this.address = entity.getAddress();
        this.district = entity.getDistrict();
        this.city = entity.getCity();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.menus = entity.getMenus().stream().map(
                MenuDto::new
        ).collect(Collectors.toList());
        this.restaurantReviews = entity.getRestaurantReviews().stream().map(
                RestaurantReviewDto::new
        ).collect(Collectors.toList());
        this.evaluation = statisticsOfEvaluation(entity.getEvaluation());
        this.rating = entity.getRating();
        this.image = entity.getImage();
        this.veganTypes = veganTypeList(veganTypes);
    }
}