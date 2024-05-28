package com.dapa.dapa.entity;

import java.sql.Blob;

import org.hibernate.annotations.UuidGenerator;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    @Id
    @UuidGenerator
    @Column(name = "id", length = 36, nullable=false)
    private String id;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "address", length = 2000)
    private String address;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "photo")
    private Blob photo;

    @OneToOne
    @JoinColumn(name="users_id", referencedColumnName = "id", nullable = false)
    private Users users;
}
