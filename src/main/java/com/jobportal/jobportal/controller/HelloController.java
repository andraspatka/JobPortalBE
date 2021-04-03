package com.jobportal.jobportal.controller;

import com.jobportal.openapi.api.HelloApi;
import com.jobportal.openapi.model.Hello;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController implements HelloApi {

    @Override
    public ResponseEntity<Hello> helloGet() {
        Hello hello = new Hello();
        hello.setId(new Random().nextLong());
        hello.setMessage("Hello World!");

        return ResponseEntity.ok(hello);
    }
}
