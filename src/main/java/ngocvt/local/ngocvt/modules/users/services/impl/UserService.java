package ngocvt.local.ngocvt.modules.users.services.impl;

import ngocvt.local.ngocvt.modules.users.entities.User;
import ngocvt.local.ngocvt.modules.users.repositories.UserRepository;
import ngocvt.local.ngocvt.modules.users.request.LoginRequest;
import ngocvt.local.ngocvt.modules.users.resources.LoginResource;
import ngocvt.local.ngocvt.modules.users.resources.UserResource;
import ngocvt.local.ngocvt.modules.users.services.interfaces.UserServiceInterface;
import ngocvt.local.ngocvt.resources.ErrorResource;
import ngocvt.local.ngocvt.services.BaseService;
import ngocvt.local.ngocvt.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends BaseService implements UserServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncode;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Object authenticate(LoginRequest request) {
        try {
            String email = request.getEmail();
            String password = request.getPassword();

            User user = userRepository.findByEmail(email).orElseThrow(() -> new BadCredentialsException("invalid email or password"));

            if(!passwordEncode.matches(password, user.getPassword())) {
                throw new BadCredentialsException("invalid email or password");
            }


            String token = jwtService.generateToken(user.getId(), user.getEmail());
            UserResource userResource = new UserResource(user.getId(), user.getEmail());
            return new LoginResource(token, userResource);


        } catch (BadCredentialsException e) {
            logger.error("Login failed for email: {}", request.getEmail(), e);
            Map<String, String> errors = new HashMap<>();
            errors.put("message", e.getMessage());
            ErrorResource errorResource = new ErrorResource("Some thing went wrong", errors);
            return errorResource;
        }
    }
}
