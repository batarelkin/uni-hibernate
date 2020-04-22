package com.example.uni.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "USER_LOGIN_UK", columnNames = "login"),
        @UniqueConstraint(name = "USER_EMAIL_UK", columnNames = "email")
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 31)
    private String login;

    @Column(nullable = false, columnDefinition = "char(62)")
    private String password;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 128)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles")
    private List<String> roles;

}
