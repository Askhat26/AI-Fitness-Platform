package smartfitness.user_profile_service.dto;



import lombok.Data;

@Data
public class UserProfileRequest {
    private String userId;
    private String name;
    private String gender;
    private int age;
    private double height;
    private double weight;
    private String fitnessGoal;
}
