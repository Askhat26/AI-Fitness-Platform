package smartfitness.user_profile_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequestDTO {

    @NotBlank
    private String name;

    @Min(10)
    private int age;

    @Positive
    private double weight;

    private String fitnessGoal;
    private String preferences;
}
