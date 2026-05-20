package ngocvt.local.ngocvt.modules.users.controllers;

import ngocvt.local.ngocvt.modules.users.dtos.LoginRequest;
import ngocvt.local.ngocvt.modules.users.dtos.LoginResponse;
import ngocvt.local.ngocvt.modules.users.services.impl.UserService;
import ngocvt.local.ngocvt.modules.users.services.interfaces.UserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final UserServiceInterface userService;
    public AuthController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse auth = userService.login(request);
        return ResponseEntity.ok(auth);
    }
}
