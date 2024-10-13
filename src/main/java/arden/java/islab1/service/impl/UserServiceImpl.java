package arden.java.islab1.service.impl;

import arden.java.islab1.api.dto.request.AdminRightsRequest;
import arden.java.islab1.api.dto.response.AdminRightsResponse;
import arden.java.islab1.api.dto.response.UserInfoResponse;
import arden.java.islab1.exception.exceptions.AlreadyAdminException;
import arden.java.islab1.model.user.AdminRequest;
import arden.java.islab1.model.user.User;
import arden.java.islab1.repository.AdminRequestRepository;
import arden.java.islab1.repository.UserRepository;
import arden.java.islab1.service.RoleService;
import arden.java.islab1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AdminRequestRepository adminRequestRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("No user with name: {}", username);
                    return new UsernameNotFoundException("No user with name: " + username);
                });
    }

    @Override
    public UserInfoResponse userInfo() {
        User user = userRepository.findById(getCurrentUser().getId()).orElseThrow();

        String role = "USER";
        if (user.getRoles().contains(roleService.getAdminRole())) {
            role = "ADMIN";
        }

        return new UserInfoResponse(user.getId(), user.getUsername(), role);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return loadUserByUsername(username);
    }

    @Override
    public AdminRightsRequest sendAdminRequest() {
        if (getCurrentUser().getRoles().contains(roleService.getAdminRole())) {
            throw new AlreadyAdminException("You are already an admin");
        }

        User user = getCurrentUser();
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setUserId(user.getId());
        adminRequest.setUsername(user.getUsername());
        adminRequestRepository.save(adminRequest);

        return new AdminRightsRequest(adminRequest.getUserId(), adminRequest.getUsername());
    }

    @Override
    public AdminRightsResponse checkAdminRights() {
        boolean isApproved = getCurrentUser().getRoles().contains(roleService.getAdminRole());

        return new AdminRightsResponse(
                getCurrentUser().getId(),
                isApproved
        );
    }
}
