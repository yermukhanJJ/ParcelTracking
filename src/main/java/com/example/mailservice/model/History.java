package com.example.mailservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "mail_history")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_send")
    private Long idSender;

    @Column(name = "id_post")
    private Long idPost;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "arrival_post")
    private String arrivalPost;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "receiving_date")
    private LocalDate receivingDate;

    public History(Long idSender, Long idPost, LocalDate arrivalDate, String arrivalPost) {
        this.idSender = idSender;
        this.idPost = idPost;
        this.arrivalDate = arrivalDate;
        this.arrivalPost = arrivalPost;
    }


}
