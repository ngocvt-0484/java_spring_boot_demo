package ngocvt.local.ngocvt.modules.users.controllers;

import jakarta.validation.Valid;
import ngocvt.local.ngocvt.modules.users.request.LoginRequest;
import ngocvt.local.ngocvt.modules.users.resources.LoginResource;
import ngocvt.local.ngocvt.modules.users.services.interfaces.UserServiceInterface;
import ngocvt.local.ngocvt.resources.ErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

@Validated
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final UserServiceInterface userService;
    public AuthController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid  @RequestBody LoginRequest request) {
        Object auth = userService.authenticate(request);

        if (auth instanceof LoginResource) {
            return ResponseEntity.ok(auth);
        } else if(auth instanceof ErrorResource errorResource) {
            return ResponseEntity.unprocessableEntity().body(errorResource);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Network error occurred");
    }
}
