package com.example.mailservice.controller;

import com.example.mailservice.model.History;
import com.example.mailservice.payload.MessageResponse;
import com.example.mailservice.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService trackingService;

    //Получение полной историй движений
    @GetMapping("/history/{id}")
    public ResponseEntity<List<History>> getHistory(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(trackingService.getAllPath(id));
    }

    //Просмотр статуса
    @GetMapping("/status/{id}")
    public ResponseEntity<Map<History, MessageResponse>> getStatus(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(trackingService.getStatusParcel(id));
    }

}
