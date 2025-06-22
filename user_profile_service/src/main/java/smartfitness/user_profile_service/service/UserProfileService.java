package smartfitness.user_profile_service.service;

import smartfitness.user_profile_service.dto.*;

public interface UserProfileService {
    UserProfileResponse createOrUpdateProfile(UserProfileRequest request);
    UserProfileResponse getProfile(String userId);
    UserProfileResponse updateProfile(String userId, UserProfileRequest request);
}
