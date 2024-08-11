package app.zerotoexpertjavaproject.mappers;

import app.zerotoexpertjavaproject.Auth.AuthRequestBody;
import app.zerotoexpertjavaproject.entities.userentity.Permission;
import app.zerotoexpertjavaproject.entities.userentity.User;

public class UserMapper {

    public static UserDTO userEntityToUserDTO(User user){
        return new UserDTO(user.getUsername());
    }

    public static User userDTOToUserEntity(UserDTO userDTO){
        return new User();
    }


    public static User userEntityFromAuthRequestBody(AuthRequestBody authRequestBody){
        return new User(authRequestBody.getUsername(),authRequestBody.getEmail(),authRequestBody.getPassword(), Permission.USER);
    }


}
