package app.zerotoexpertjavaproject.services;

import app.zerotoexpertjavaproject.entities.userentity.User;
import app.zerotoexpertjavaproject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public boolean isUserPresent(String username){
        return userRepository.existsUserByUsername(username);
    }



}
