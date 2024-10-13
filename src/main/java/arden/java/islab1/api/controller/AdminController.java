package arden.java.islab1.api.controller;

import arden.java.islab1.api.dto.request.AdminRightsRequest;
import arden.java.islab1.api.dto.response.AdminRightsResponse;
import arden.java.islab1.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/list")
    public ResponseEntity<List<AdminRightsRequest>> getAdminRequests() {
        return ResponseEntity.ok(adminService.getAllRequestRights());
    }

    @PostMapping("/process/{userId}")
    public ResponseEntity<AdminRightsResponse> addAdminRights(@PathVariable Long userId, @RequestParam Boolean isApproved) {
        return ResponseEntity.ok(adminService.processAdminRights(userId, isApproved));
    }
}
