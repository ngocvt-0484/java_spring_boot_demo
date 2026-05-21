package ngocvt.local.ngocvt.modules.users.services.interfaces;

import ngocvt.local.ngocvt.modules.users.request.LoginRequest;
import ngocvt.local.ngocvt.modules.users.resources.LoginResource;

public interface UserServiceInterface {
    Object authenticate(LoginRequest request);
}
