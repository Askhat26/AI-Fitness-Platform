package smartfitness.user_profile_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartfitness.user_profile_service.client.AuthClient;
import smartfitness.user_profile_service.dto.*;
import smartfitness.user_profile_service.service.UserProfileService;


@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final AuthClient authClient;

    @PostMapping
    public ResponseEntity<UserProfileResponse> createOrUpdate(
            @RequestBody UserProfileRequest request,
            @RequestHeader("Authorization") String token) {

        if (!authClient.validateToken(token)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(userProfileService.createOrUpdateProfile(request));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @PathVariable String userId,
            @RequestBody UserProfileRequest request,
            @RequestHeader("Authorization") String token) {

        if (!authClient.validateToken(token)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(userProfileService.updateProfile(userId, request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getProfile(
            @PathVariable String userId,
            @RequestHeader("Authorization") String token) {

        if (!authClient.validateToken(token)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(userProfileService.getProfile(userId));
    }
}
