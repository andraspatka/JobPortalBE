package com.jobportal.jobportal.controller;

import com.jobportal.openapi.api.HelloApi;
import com.jobportal.openapi.model.Hello;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController implements HelloApi {
    @Override
    public ResponseEntity<List<Hello>> helloGet() {
        Hello hello1 = new Hello();
        hello1.setId(100L);
        hello1.setMessage("Hello message 1!");

        Hello hello2 = new Hello();
        hello2.setId(200L);
        hello2.setMessage("Hello message 2");

        return ResponseEntity.ok(List.of(hello1, hello2));
    }
}
