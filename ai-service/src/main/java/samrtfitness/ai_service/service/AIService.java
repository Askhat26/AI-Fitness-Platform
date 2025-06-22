package samrtfitness.ai_service.service;

import samrtfitness.ai_service.dto.*;
import samrtfitness.ai_service.exception.AIProcessingException;

public interface AIService {
    WorkoutRecommendationResponse recommendWorkout(WorkoutRecommendationRequest request) throws AIProcessingException;
    NutritionResponse recommendNutrition(NutritionRequest request) throws AIProcessingException;
    ProgressSimulationResponse simulateProgress(ProgressSimulationRequest request) throws AIProcessingException;
}