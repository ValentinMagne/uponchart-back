package com.costardstudio.uponchart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AliveController {

    @GetMapping("/alive")
    public ResponseEntity<Void> getHello() {
        return ResponseEntity.status(204).build();
    }

}
