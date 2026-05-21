package ngocvt.local.ngocvt.modules.users.services.impl;

import ngocvt.local.ngocvt.modules.users.entities.User;
import ngocvt.local.ngocvt.modules.users.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) {
        Long id = Long.parseLong(userId);
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
