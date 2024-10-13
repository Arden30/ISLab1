package arden.java.islab1.api.controller;

import arden.java.islab1.api.dto.request.AdminRightsRequest;
import arden.java.islab1.api.dto.response.AdminRightsResponse;
import arden.java.islab1.api.dto.response.UserInfoResponse;
import arden.java.islab1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo() {
        return ResponseEntity.ok(userService.userInfo());
    }

    @PostMapping("/rights/request")
    public ResponseEntity<AdminRightsRequest> sendAdminRequest() {
        return ResponseEntity.ok(userService.sendAdminRequest());
    }

    @GetMapping("/rights/response/")
    public ResponseEntity<AdminRightsResponse> checkAdminResponse() {
        return ResponseEntity.ok(userService.checkAdminRights());
    }
}
