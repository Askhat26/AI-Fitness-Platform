package samrtfitness.ai_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import samrtfitness.ai_service.dto.*;
import samrtfitness.ai_service.exception.AIProcessingException;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public WorkoutRecommendationResponse recommendWorkout(WorkoutRecommendationRequest request) throws AIProcessingException {
        try {
            Object response = rabbitTemplate.convertSendAndReceive("ai.exchange", "ai.workout.recommend", request);
            return objectMapper.convertValue(response, WorkoutRecommendationResponse.class);
        } catch (Exception e) {
            throw new AIProcessingException("Error processing workout recommendation", e);
        }
    }

    @Override
    public NutritionResponse recommendNutrition(NutritionRequest request) throws AIProcessingException {
        try {
            Object response = rabbitTemplate.convertSendAndReceive("ai.exchange", "ai.nutrition.recommend", request);
            return objectMapper.convertValue(response, NutritionResponse.class);
        } catch (Exception e) {
            throw new AIProcessingException("Error processing nutrition recommendation", e);
        }
    }

    @Override
    public ProgressSimulationResponse simulateProgress(ProgressSimulationRequest request) throws AIProcessingException {
        try {
            Object response = rabbitTemplate.convertSendAndReceive("ai.exchange", "ai.progress.simulate", request);
            return objectMapper.convertValue(response, ProgressSimulationResponse.class);
        } catch (Exception e) {
            throw new AIProcessingException("Error processing progress simulation", e);
        }
    }
}
