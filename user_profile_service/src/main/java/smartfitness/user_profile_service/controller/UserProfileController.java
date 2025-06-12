package smartfitness.user_profile_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartfitness.user_profile_service.dto.UpdateProfileRequestDTO;
import smartfitness.user_profile_service.dto.UserProfileDTO;
import smartfitness.user_profile_service.model.UserProfile;
import smartfitness.user_profile_service.service.UserProfileService;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService service;

    @PostMapping("/create")
    public ResponseEntity<UserProfile> create(@Valid @RequestBody UserProfileDTO dto) {
        return ResponseEntity.ok(service.createProfile(dto));
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<UserProfile> update(
            @PathVariable String email,
            @Valid @RequestBody UpdateProfileRequestDTO dto) {
        return ResponseEntity.ok(service.updateUserProfile(email, dto));
    }

    // NEW: Get user profile by email
    @GetMapping("/{email}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable String email) {
        return ResponseEntity.ok(service.getProfileByEmail(email));
    }
}