package smartfitness.user_profile_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import smartfitness.user_profile_service.config.RabbitMQConfig;
import smartfitness.user_profile_service.dto.*;
import smartfitness.user_profile_service.entity.UserProfile;
import smartfitness.user_profile_service.repository.UserProfileRepository;



@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public UserProfileResponse createOrUpdateProfile(UserProfileRequest request) {
        UserProfile profile = UserProfile.builder()
                .userId(request.getUserId())
                .name(request.getName())
                .gender(request.getGender())
                .age(request.getAge())
                .height(request.getHeight())
                .weight(request.getWeight())
                .fitnessGoal(request.getFitnessGoal())
                .build();
        UserProfile saved = repository.save(profile);

        // Emit event to RabbitMQ
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "profile.updated", saved);

        return toResponse(saved);
    }

    @Override
    public UserProfileResponse getProfile(String userId) {
        UserProfile profile = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user: " + userId));
        return toResponse(profile);
    }

    @Override
    public UserProfileResponse updateProfile(String userId, UserProfileRequest request) {
        UserProfile profile = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setName(request.getName());
        profile.setGender(request.getGender());
        profile.setAge(request.getAge());
        profile.setHeight(request.getHeight());
        profile.setWeight(request.getWeight());
        profile.setFitnessGoal(request.getFitnessGoal());

        UserProfile updated = repository.save(profile);


        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "profile.updated", updated);

        return toResponse(updated);
    }

    private UserProfileResponse toResponse(UserProfile profile) {
        return UserProfileResponse.builder()
                .userId(profile.getUserId())
                .name(profile.getName())
                .gender(profile.getGender())
                .age(profile.getAge())
                .height(profile.getHeight())
                .weight(profile.getWeight())
                .fitnessGoal(profile.getFitnessGoal())
                .build();
    }
}