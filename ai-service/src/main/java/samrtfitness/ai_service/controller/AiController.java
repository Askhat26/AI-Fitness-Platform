package samrtfitness.ai_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samrtfitness.ai_service.dto.*;
import samrtfitness.ai_service.exception.AIProcessingException;
import samrtfitness.ai_service.service.AIService;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AIService aiService;

    @PostMapping("/recommend-workout")
    public ResponseEntity<WorkoutRecommendationResponse> recommendWorkout(@RequestBody WorkoutRecommendationRequest request) throws AIProcessingException {
        return ResponseEntity.ok(aiService.recommendWorkout(request));
    }

    @PostMapping("/recommend-nutrition")
    public ResponseEntity<NutritionResponse> recommendNutrition(@RequestBody NutritionRequest request) throws AIProcessingException {
        return ResponseEntity.ok(aiService.recommendNutrition(request));
    }

    @PostMapping("/simulate-progress")
    public ResponseEntity<ProgressSimulationResponse> simulateProgress(@RequestBody ProgressSimulationRequest request) throws AIProcessingException {
        return ResponseEntity.ok(aiService.simulateProgress(request));
    }
}
