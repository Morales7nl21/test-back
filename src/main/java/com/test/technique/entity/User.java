package com.test.technique.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * Represents a user entity in the system.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "c_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User's first name
    @NotNull
    @NotBlank
    @Size(min = 3, max = 40, message = "Name should not be less than 3 and Name should not be greater than 40" )
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    // User's  father's last name
    @NotNull
    @NotBlank
    @Size(min = 3, max = 30, message = "Father's Lastname should not be less than 3 characters and Father's Lastname should not be greater than 30 characters")
    @Column(name = "fathers_lastname", nullable = false, length = 30)
    private String fathersLastname;

    // User's  mother's last name
    @Size(max = 30, message = "Mothers's Lastname should not be greater than 30 characters")
    @Column(name = "mothers_lastname", length = 30)
    private String mothersLastname;

    // User's phone number
    @Size(min = 8, max = 15, message = "Phone number should be between 8 and 15 characters")
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    // User's prefix phone number
    @Size(min = 2, max = 5, message = "Prefix number should be between 2 and 5 characters")
    @Column(name = "prefix_number", length = 5)
    private String prefixNumber;

    // User's email
    @NotBlank
    @Size(min = 4, max = 255, message = "Email should be between 4 and 256 characters")
    @Email(message = "Invalid email format")
    @Column(unique = true, name="email", nullable = false)
    private String email;

    // User's birthdate
    @NotNull
    @Temporal(TemporalType.DATE)
    @PastOrPresent
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    // User's data to manage persisting data
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    // User's data to manage persisting data
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    /**
     * Sets the creation timestamp before persisting the user.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    /**
     * Sets the update timestamp before updating the user.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

}