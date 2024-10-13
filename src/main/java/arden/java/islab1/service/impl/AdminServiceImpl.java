package arden.java.islab1.service.impl;

import arden.java.islab1.api.dto.request.AdminRightsRequest;
import arden.java.islab1.api.dto.response.AdminRightsResponse;
import arden.java.islab1.model.user.AdminRequest;
import arden.java.islab1.model.user.User;
import arden.java.islab1.repository.AdminRequestRepository;
import arden.java.islab1.service.AdminService;
import arden.java.islab1.service.RoleService;
import arden.java.islab1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRequestRepository adminRequestRepository;
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public List<AdminRightsRequest> getAllRequestRights() {
        return adminRequestRepository.findAll().stream().map(request -> new AdminRightsRequest(request.getUserId(), request.getUsername())).toList();
    }

    @Override
    public AdminRightsResponse processAdminRights(Long userId, Boolean isApproved) {
        Optional<AdminRequest> adminRequest = adminRequestRepository.findByUserId(userId);
        if (adminRequest.isPresent()) {
            if (isApproved) {
                User user = userService.loadUserByUsername(adminRequest.get().getUsername());
                user.getRoles().add(roleService.getAdminRole());
                userService.saveUser(user);
            }
            adminRequestRepository.delete(adminRequest.get());

            return new AdminRightsResponse(adminRequest.get().getUserId(), isApproved);
        }

        throw new NoSuchElementException("No such admin request with id " + userId);
    }
}
