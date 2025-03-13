package com.example.demo.controllers;

import com.example.demo.models.ComplexObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ComplexObjectController {

    @PostMapping("/complex")
    public ResponseEntity<ComplexObject> receiveComplexObject(@RequestBody ComplexObject complexObject) {
        System.out.println("Received: " + complexObject);
        return ResponseEntity.ok(complexObject);
    }
}

