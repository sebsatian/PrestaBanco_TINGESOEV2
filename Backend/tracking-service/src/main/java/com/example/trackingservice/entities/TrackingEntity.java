package com.example.trackingservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tracking_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrackingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_id")
    private int requestId;

    @Column(name = "client_rut")
    private String clientRut;

    @Column(name = "status")
    private String status;


}
