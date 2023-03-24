package com.app.vple.repository;

import com.app.vple.domain.Evaluation;
import com.app.vple.domain.RecommandRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    Evaluation findByRecommandRestaurant(RecommandRestaurant recommandRestaurant);
}
