package samrtfitness.ai_service.dto;

import lombok.Data;

@Data
public class WorkoutRecommendationRequest {
    private String goal;
    private String fitnessLevel;
    private int durationInWeeks;
}