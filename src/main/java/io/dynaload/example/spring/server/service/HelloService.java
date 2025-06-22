package io.dynaload.example.spring.server.service;

import io.dynaload.annotations.DynaloadCallable;
import io.dynaload.annotations.DynaloadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@DynaloadService
@RestController
public class HelloService {

    @DynaloadCallable
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    @GetMapping("/get")
    public String get(){
        return "ok";
    }
}
