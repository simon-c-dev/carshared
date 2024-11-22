package com.coderunners.authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String city;
    private String phone;
    private String car;
    private String driverLicense;
    private boolean isBlocked;
    private int rating;


    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}