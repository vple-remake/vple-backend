package com.app.vple.repository;

import com.app.vple.domain.RecommandRestaurant;
import com.app.vple.domain.RestaurantReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long> {

    Page<RestaurantReview> findByRecommandRestaurant(RecommandRestaurant recommandRestaurant, Pageable pageable);
}
