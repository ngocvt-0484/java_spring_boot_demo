package ngocvt.local.ngocvt.modules.users.services.impl;

import ngocvt.local.ngocvt.modules.users.request.LoginRequest;
import ngocvt.local.ngocvt.modules.users.resources.LoginResource;
import ngocvt.local.ngocvt.modules.users.resources.UserResource;
import ngocvt.local.ngocvt.modules.users.services.interfaces.UserServiceInterface;
import ngocvt.local.ngocvt.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService implements UserServiceInterface {

    @Override
    public LoginResource login(LoginRequest request) {
        try {
            String email = request.getEmail();
            String password = request.getPassword();

            if (1 == 1) {
                String token = "random token";
                UserResource user = new UserResource(1L,  "haha@gmail.com");
                return new LoginResource(token, user);

            } else {
                throw new RuntimeException("invalid email or password");
            }
        } catch (Exception e) {
            throw new RuntimeException("something wrong");
        }
    }
}
