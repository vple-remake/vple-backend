package com.app.vple.domain.dto;

import com.app.vple.domain.Cart;
import lombok.Data;

@Data
public class MyCartDto {

    private Long id;

    private Long restaurantId;

    private String name;

    private String address;

    private String image;

    public MyCartDto(Cart entity) {
        this.id = entity.getId();
        this.restaurantId = entity.getRestaurantId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.image = entity.getImage();
    }
}
