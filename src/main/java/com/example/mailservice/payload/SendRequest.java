package com.example.mailservice.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SendRequest {

    private String type;

    private String index;

    private String address;

    private String recipientName;

    private Long idPost;

    private LocalDate date;
}
