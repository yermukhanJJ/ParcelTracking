package com.example.mailservice.controller;

import com.example.mailservice.model.History;
import com.example.mailservice.model.Sender;
import com.example.mailservice.payload.DateRequest;
import com.example.mailservice.payload.SendRequest;
import com.example.mailservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //Регистрация почтового отправления
    @PostMapping("/sender")
    public ResponseEntity<Sender> registration(@RequestBody SendRequest request) {
        return ResponseEntity.ok(postService.sendMail(request));
    }

    //Прибытие в почту
    @PostMapping("/arrival-parcel/{id-parcel}/{id-post}")
    public ResponseEntity<History> arrival(@PathVariable(value = "id-parcel") Long idParcel,
                                           @PathVariable(value = "id-post") Long idPost,
                                           @RequestBody DateRequest request) {
        return ResponseEntity.ok(postService.arrivalParcelStatus(idParcel, idPost, request.getDate()));
    }

    //Убытие посылки
    @PutMapping("/departure/{id-parcel}/{id-post}")
    public ResponseEntity<History> departure(@PathVariable(value = "id-parcel") Long idParcel,
                                             @PathVariable(value = "id-post") Long idPost,
                                             @RequestBody DateRequest request) {
        return ResponseEntity.ok(postService.departureParcelStatus(idParcel, idPost, request.getDate()));
    }

    //Получение посылки
    @PutMapping("/receiving/{id-parcel}")
    public ResponseEntity<History> receiving(@PathVariable(value = "id-parcel") Long idParcel,
                                             @RequestBody DateRequest request) {
        return ResponseEntity.ok(postService.receivingParcelStatus(request.getDate(), idParcel));
    }

}
