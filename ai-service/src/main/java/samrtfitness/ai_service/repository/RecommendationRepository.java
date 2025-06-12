package samrtfitness.ai_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samrtfitness.ai_service.model.Recommendation;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUserId(String userId);
    Optional<Recommendation> findByActivityId(String activityId);


    List<Recommendation> findByActivityType(String activityType);
    List<Recommendation> findByUserIdOrderByCreatedAtDesc(String userId);
    Optional<Recommendation> findTopByUserIdOrderByCreatedAtDesc(String userId);
    List<Recommendation> findByUserIdAndActivityType(String userId, String activityType);
}