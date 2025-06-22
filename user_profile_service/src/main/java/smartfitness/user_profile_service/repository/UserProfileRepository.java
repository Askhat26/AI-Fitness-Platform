package smartfitness.user_profile_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smartfitness.user_profile_service.entity.UserProfile;


public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
}
