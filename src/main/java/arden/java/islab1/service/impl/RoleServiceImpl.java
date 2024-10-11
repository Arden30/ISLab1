package arden.java.islab1.service.impl;

import arden.java.islab1.model.user.Role;
import arden.java.islab1.repository.RoleRepository;
import arden.java.islab1.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getUserRole() {
        return roleRepository.findByRole("USER").get();
    }

    @Override
    public Role getAdminRole() {
        return roleRepository.findByRole("ADMIN").get();
    }
}
