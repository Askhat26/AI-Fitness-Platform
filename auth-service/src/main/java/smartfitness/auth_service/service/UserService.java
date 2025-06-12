package smartfitness.auth_service.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import smartfitness.auth_service.model.User;
import smartfitness.auth_service.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}