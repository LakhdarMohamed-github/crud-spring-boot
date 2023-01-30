package com.myProject.myProject.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;

@Entity
@Table(name = "_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(length = 15, nullable = false)
    private String password;
    @Column(length = 45, nullable = false, name = "first_name")
    private String firstName;
    @Column(length = 45, nullable = false, name = "last_name")
    private String lastName;
    private boolean enabled;

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
