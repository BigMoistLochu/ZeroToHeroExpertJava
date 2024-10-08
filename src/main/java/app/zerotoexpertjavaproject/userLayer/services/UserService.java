package app.zerotoexpertjavaproject.userLayer.services;

import app.zerotoexpertjavaproject.models.AuthRequestBody;
import app.zerotoexpertjavaproject.userLayer.entities.userentity.User;
import app.zerotoexpertjavaproject.exceptions.UserAlreadyExistsException;
import app.zerotoexpertjavaproject.userLayer.mappers.dtos.UserDTO;
import app.zerotoexpertjavaproject.userLayer.mappers.UserMapper;
import app.zerotoexpertjavaproject.userLayer.repositories.UserRepository;
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

        if(savedUser == null) throw new IllegalArgumentException("User can't be created");

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



