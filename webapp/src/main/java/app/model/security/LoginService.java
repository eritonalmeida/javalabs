package app.model.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import app.model.entity.UserEntity;
import app.model.repository.UserRepository;

public class LoginService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRepository repository = new UserRepository();
        UserEntity user = repository.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        return new CustomUserDetails(user);
    }
}
