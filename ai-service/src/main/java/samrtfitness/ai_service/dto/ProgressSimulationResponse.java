package samrtfitness.ai_service.dto;

import lombok.Data;

@Data
public class ProgressSimulationResponse {
    private String forecast;
    private double estimatedWeight;
}