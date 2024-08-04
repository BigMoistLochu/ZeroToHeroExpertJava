package app.zerotoexpertjavaproject.entities.userentity;

import java.util.List;

public enum Permission {

    USER,ADMIN,MODERATOR;
    public List<Permission> getPermissions(){
        return List.of(USER,ADMIN,MODERATOR);
    }
}
