package app.zerotoexpertjavaproject.entities.tokenentity;

import app.zerotoexpertjavaproject.entities.userentity.User;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
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
