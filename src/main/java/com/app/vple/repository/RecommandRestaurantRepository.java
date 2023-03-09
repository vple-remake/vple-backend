package com.app.vple.repository;

import com.app.vple.domain.RecommandRestaurant;
import com.app.vple.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommandRestaurantRepository extends JpaRepository<RecommandRestaurant, Long> {

    RecommandRestaurant findById(User user);

    Page<RecommandRestaurant> findAll(Pageable pageable);

    List<RecommandRestaurant> findByNameContaining(String keyword);

    List<RecommandRestaurant> findByAddressContaining(String keyword);

    Page<RecommandRestaurant> findByDistrict(String district, Pageable pageable);

    Page<RecommandRestaurant> findByDistrictAndCity(String district, String city, Pageable pageable);
}
