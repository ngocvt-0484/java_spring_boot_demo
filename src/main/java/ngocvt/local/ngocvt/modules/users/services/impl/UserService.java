package ngocvt.local.ngocvt.modules.users.services.impl;

import ngocvt.local.ngocvt.modules.users.dtos.LoginRequest;
import ngocvt.local.ngocvt.modules.users.dtos.LoginResponse;
import ngocvt.local.ngocvt.modules.users.dtos.UserDTO;
import ngocvt.local.ngocvt.modules.users.services.interfaces.UserServiceInterface;
import ngocvt.local.ngocvt.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService implements UserServiceInterface {

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            String email = request.getEmail();
            String password = request.getPassword();

            if (1 == 1) {
                String token = "random token";
                UserDTO user = new UserDTO(1L,  "haha@gmail.com");
                return new LoginResponse(token, user);

            } else {
                throw new RuntimeException("invalid email or password");
            }
        } catch (Exception e) {
            throw new RuntimeException("something wrong");
        }
    }
}
