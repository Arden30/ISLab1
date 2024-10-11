package arden.java.islab1.service;

import arden.java.islab1.model.user.Role;

public interface RoleService {
    Role getUserRole();

    Role getAdminRole();
}
