package com.interviewprep.service;

import com.interviewprep.dto.AuthRequest;
import com.interviewprep.dto.AuthResponse;
import com.interviewprep.dto.ChangePasswordRequest;
import com.interviewprep.dto.RegisterRequest;
import com.interviewprep.dto.UserDto;
import com.interviewprep.entity.Role;
import com.interviewprep.entity.User;
import com.interviewprep.exception.BadRequestException;
import com.interviewprep.exception.ResourceNotFoundException;
import com.interviewprep.repository.UserRepository;
import com.interviewprep.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        String normalizedEmail = req.getEmail() != null ? req.getEmail().trim().toLowerCase() : "";
        if (userRepository.findByEmail(normalizedEmail).isPresent() || userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email is already registered: " + normalizedEmail);
        }

        User user = new User();
        user.setName(req.getName() != null && !req.getName().isBlank() ? req.getName().trim() : deriveNameFromEmail(normalizedEmail));
        user.setEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setCollege(req.getCollege() != null && !req.getCollege().isBlank() ? req.getCollege() : "Engineering College");
        user.setDegree(req.getDegree() != null && !req.getDegree().isBlank() ? req.getDegree() : "B.Tech");
        user.setBranch(req.getBranch() != null && !req.getBranch().isBlank() ? req.getBranch() : "Computer Science");
        user.setGraduationYear(req.getGraduationYear() != null ? req.getGraduationYear() : 2025);
        user.setRole(req.getRole() != null ? req.getRole() : Role.ROLE_STUDENT);
        user.setStreakDays(1);

        user = userRepository.saveAndFlush(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(normalizedEmail, req.getPassword())
        );

        String jwt = tokenProvider.generateToken(authentication);

        return new AuthResponse(jwt, user.getId(), user.getName(), user.getEmail(), user.getRole().name(), user.getCollege());
    }

    @Transactional
    public AuthResponse login(AuthRequest req) {
        String normalizedEmail = req.getEmail() != null ? req.getEmail().trim().toLowerCase() : "";
        String rawPassword = req.getPassword() != null ? req.getPassword() : "";

        // 1. Check if user is registered in the database
        Optional<User> existingUser = userRepository.findByEmail(normalizedEmail)
                .or(() -> userRepository.findByEmail(req.getEmail()));

        if (existingUser.isEmpty()) {
            throw new BadRequestException("Email not registered. Please register first.");
        }

        // 2. Verify credentials
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(normalizedEmail, rawPassword)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);

            User user = existingUser.get();
            return new AuthResponse(jwt, user.getId(), user.getName(), user.getEmail(), user.getRole().name(), user.getCollege());
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new BadRequestException("Password invalid. Please try again.");
        }
    }

    private String deriveNameFromEmail(String email) {
        if (email == null || email.isBlank()) {
            return "Student User";
        }
        try {
            String prefix = email.split("@")[0].replaceAll("[._-]+", " ").trim();
            if (prefix.isBlank()) return "Student User";
            String[] parts = prefix.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String part : parts) {
                if (!part.isEmpty()) {
                    sb.append(Character.toUpperCase(part.charAt(0)))
                      .append(part.substring(1).toLowerCase())
                      .append(" ");
                }
            }
            return sb.toString().trim();
        } catch (Exception e) {
            return "Student User";
        }
    }

    public User getCurrentAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new BadRequestException("No authenticated user found");
        }
        return userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserDto getCurrentUserProfile() {
        return UserDto.fromEntity(getCurrentAuthenticatedUser());
    }

    @Transactional
    public void changePassword(ChangePasswordRequest req) {
        String emailToUse = null;

        // 1. If user is currently authenticated via JWT, get their email
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            emailToUse = auth.getName();
        }

        // 2. If not authenticated or email explicitly provided in request
        if ((emailToUse == null || emailToUse.isBlank()) && req.getEmail() != null && !req.getEmail().isBlank()) {
            emailToUse = req.getEmail().trim().toLowerCase();
        }

        if (emailToUse == null || emailToUse.isBlank()) {
            throw new BadRequestException("Email is required to change password.");
        }

        String normalizedEmail = emailToUse.trim().toLowerCase();
        User user = userRepository.findByEmail(normalizedEmail)
                .or(() -> userRepository.findByEmail(req.getEmail()))
                .orElseThrow(() -> new BadRequestException("Email not registered. Please register first."));

        // 3. Verify old password
        if (req.getOldPassword() == null || !passwordEncoder.matches(req.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Incorrect old password. Please enter correct old password.");
        }

        // 4. Validate new password
        if (req.getNewPassword() == null || req.getNewPassword().trim().length() < 6) {
            throw new BadRequestException("New password must be at least 6 characters long.");
        }

        if (passwordEncoder.matches(req.getNewPassword().trim(), user.getPassword())) {
            throw new BadRequestException("New password cannot be the same as the old password.");
        }

        // 5. Replace old password with new password
        user.setPassword(passwordEncoder.encode(req.getNewPassword().trim()));
        userRepository.saveAndFlush(user);
    }
}
