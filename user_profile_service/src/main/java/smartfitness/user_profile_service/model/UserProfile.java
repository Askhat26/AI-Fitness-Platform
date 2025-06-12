package smartfitness.user_profile_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    private String email; // Linking with auth-service (email-based)

    private String name;
    private int age;
    private double weight;
    private String goal;
    private String preferences;
}