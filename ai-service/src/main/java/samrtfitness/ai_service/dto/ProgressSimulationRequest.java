package samrtfitness.ai_service.dto;

import lombok.Data;

@Data
public class ProgressSimulationRequest {
    private String goal;
    private int weeks;
    private int currentWeight;
}