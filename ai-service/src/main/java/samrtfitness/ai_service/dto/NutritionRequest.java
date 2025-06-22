package samrtfitness.ai_service.dto;

import lombok.Data;

@Data
public class NutritionRequest {
    private String dietType;
    private int dailyCalories;
}