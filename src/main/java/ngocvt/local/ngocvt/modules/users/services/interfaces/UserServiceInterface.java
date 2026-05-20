package ngocvt.local.ngocvt.modules.users.services.interfaces;

import ngocvt.local.ngocvt.modules.users.dtos.LoginRequest;
import ngocvt.local.ngocvt.modules.users.dtos.LoginResponse;

public interface UserServiceInterface {
    LoginResponse login(LoginRequest request);
}
