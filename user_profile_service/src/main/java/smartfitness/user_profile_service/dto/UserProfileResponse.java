package smartfitness.user_profile_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {
    private String userId;
    private String name;
    private String gender;
    private int age;
    private double height;
    private double weight;
    private String fitnessGoal;
}
