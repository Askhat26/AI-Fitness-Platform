package smartfitness.user_profile_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {
    @Id
    private String userId;

    private String name;
    private String gender;
    private int age;
    private double height;
    private double weight;
    private String fitnessGoal;
}