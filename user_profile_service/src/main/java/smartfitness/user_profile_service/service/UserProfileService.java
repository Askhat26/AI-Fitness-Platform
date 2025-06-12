package smartfitness.user_profile_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import smartfitness.user_profile_service.config.RabbitMQConfig;
import smartfitness.user_profile_service.dto.UpdateProfileRequestDTO;
import smartfitness.user_profile_service.dto.UserProfileDTO;
import smartfitness.user_profile_service.model.UserProfile;
import smartfitness.user_profile_service.repository.UserProfileRepository;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public UserProfile createProfile(UserProfileDTO dto) {
        UserProfile profile = UserProfile.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .age(dto.getAge())
                .weight(dto.getWeight())
                .goal(dto.getGoal())
                .preferences(dto.getPreferences())
                .build();

        UserProfile saved = repository.save(profile);

        // Publish to RabbitMQ
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, saved.getEmail());
        return saved;
    }

    public UserProfile updateUserProfile(String email, UpdateProfileRequestDTO dto) {
        UserProfile existing = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existing.setName(dto.getName());
        existing.setAge(dto.getAge());
        existing.setWeight(dto.getWeight());
        existing.setGoal(dto.getFitnessGoal());
        existing.setPreferences(dto.getPreferences());

        return repository.save(existing);
    }
    public UserProfile getProfileByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profile not found for email: " + email));
    }
}
