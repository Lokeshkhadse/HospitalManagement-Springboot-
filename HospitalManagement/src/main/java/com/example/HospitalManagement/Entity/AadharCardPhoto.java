package com.example.HospitalManagement.Entity;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "aadhar_card_photo")
public class AadharCardPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long patientId;

    @Lob
    @Column(name = "photo")
    private byte[] photo;
}
