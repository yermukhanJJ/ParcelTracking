package com.example.mailservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "index")
    private String index;

    @Column(name = "address")
    private String address;

    @Column(name = "recipient_name")
    private String recipientName;

    public Sender(String type, String index, String address, String recipientName) {
        this.type = type;
        this.index = index;
        this.address = address;
        this.recipientName = recipientName;
    }

}
