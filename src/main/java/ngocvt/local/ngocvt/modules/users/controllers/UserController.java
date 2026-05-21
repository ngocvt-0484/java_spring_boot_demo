package ngocvt.local.ngocvt.modules.users.controllers;

import ngocvt.local.ngocvt.modules.users.entities.User;
import ngocvt.local.ngocvt.modules.users.repositories.UserRepository;
import ngocvt.local.ngocvt.modules.users.resources.UserResource;
import ngocvt.local.ngocvt.resources.SuccessResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("me")
    public ResponseEntity<?> me() {
        String email = "admin3@gmail.com";
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            UserResource userResource = new UserResource(user.getId(), user.getEmail(), user.getName());
            SuccessResource successResource = new SuccessResource("User found successfully", userResource);
            return ResponseEntity.ok(successResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
