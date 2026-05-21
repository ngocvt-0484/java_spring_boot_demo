package ngocvt.local.ngocvt.modules.users.controllers;

import jakarta.validation.Valid;
import ngocvt.local.ngocvt.modules.users.request.LoginRequest;
import ngocvt.local.ngocvt.modules.users.resources.LoginResource;
import ngocvt.local.ngocvt.modules.users.services.interfaces.UserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

@Validated
@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final UserServiceInterface userService;
    public AuthController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResource> login(@Valid  @RequestBody LoginRequest request) {
        LoginResource auth = userService.login(request);
        return ResponseEntity.ok(auth);
    }
}
