package smartfitness.user_profile_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(10)
    private int age;

    @Positive
    private double weight;

    private String goal;
    private String preferences;
}
