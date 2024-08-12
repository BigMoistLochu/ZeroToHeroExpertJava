package app.zerotoexpertjavaproject.userLayer.entities.tokenentity;

import app.zerotoexpertjavaproject.userLayer.entities.userentity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Token {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "token_cid")
    private String token;

    private Timestamp createdTime;

    private Timestamp expiredTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
