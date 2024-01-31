package com.test.technique.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "c_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Street is required")
    @Size(max = 255, message = "Street should not exceed 255 characters")
    @Column(name = "street")
    private String street;

    @Size(max = 10, message = "Interior number should not exceed 10 characters")
    @Column(name = "interior_number")
    private String interiorNumber;

    @Size(max = 10, message = "Exterior number should not exceed 10 characters")
    @Column(name = "exterior_number")
    private String exteriorNumber;

    @Size(max = 255, message = "Colony should not exceed 255 characters")
    @Column(name = "colony")
    private String colony;

    @Size(max = 255, message = "Municipality should not exceed 255 characters")
    @Column(name = "municipality")
    private String municipality;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private User user;

    // adress's data to manage persisting data
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    // adress's data to manage persisting data
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    /**
     * Sets the creation timestamp before persisting the adress.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    /**
     * Sets the update timestamp before updating the adress.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}