package com.kunsas.grabgoods.userservice.entity;

import com.kunsas.grabgoods.userservice.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private UserRole role;
    private String mobileNumber;
    private String imageUrl;
    private String address;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}

