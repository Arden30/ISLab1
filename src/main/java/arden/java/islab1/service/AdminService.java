package arden.java.islab1.service;

import arden.java.islab1.api.dto.request.AdminRightsRequest;
import arden.java.islab1.api.dto.response.AdminRightsResponse;

import java.util.List;

public interface AdminService {
    List<AdminRightsRequest> getAllRequestRights();

    AdminRightsResponse processAdminRights(Long userId, Boolean isApproved);
}
