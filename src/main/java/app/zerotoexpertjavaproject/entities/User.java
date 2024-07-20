package app.zerotoexpertjavaproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "_user")
public class User implements UserDetails {


    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String lastname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Permission permission;

    public User(){}
    public User(String username, String lastname, String email, String password, Permission permission){
        this.username = username;
        this.lastname = lastname;
        this.email= email;
        this.password = password;
        this.permission = permission;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //powinno sie zwracac Tylko permissje ktore aktualnie
//        List<GrantedAuthority> authorities = permission.getPermissions().stream().map(
//                permissionEnum -> {
//                    return new SimpleGrantedAuthority(permissionEnum.name());
//                }
//        ).collect(Collectors.toList());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + permission.name()));
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }




}
