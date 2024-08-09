package app.zerotoexpertjavaproject.services;

import app.zerotoexpertjavaproject.Auth.AuthRequestBody;
import app.zerotoexpertjavaproject.entities.userentity.User;
import app.zerotoexpertjavaproject.exceptions.UserAlreadyExistsException;
import app.zerotoexpertjavaproject.mappers.UserDTO;
import app.zerotoexpertjavaproject.mappers.UserMapper;
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

    public UserDTO createUser(AuthRequestBody authRequestBody){
        if(isUserPresent(authRequestBody.getUsername())) throw new UserAlreadyExistsException("User already exists");
        if(!validateAuthRequestBodyData(authRequestBody)) throw new IllegalArgumentException("Invalid data");
        User userToSave = UserMapper.userEntityFromAuthRequestBody(authRequestBody);
        User savedUser = userRepository.save(userToSave);
        return UserMapper.userEntityToUserDTO(savedUser);
    }

    public boolean validateAuthRequestBodyData(AuthRequestBody authRequestBody){

        return !(authRequestBody.getUsername() == null)
                && authRequestBody.getUsername().length() <= 30
                && !authRequestBody.getUsername().isBlank()
                && !(authRequestBody.getPassword() == null)
                && authRequestBody.getPassword().length() <= 30
                && !authRequestBody.getPassword().isBlank()
                && !(authRequestBody.getEmail() == null)
                && authRequestBody.getEmail().length() <= 30
                && !authRequestBody.getEmail().isBlank()
                && authRequestBody.getEmail().contains("@");
    }



}



