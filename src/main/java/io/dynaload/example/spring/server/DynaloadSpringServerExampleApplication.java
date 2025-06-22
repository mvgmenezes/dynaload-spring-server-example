package io.dynaload.example.spring.server;

import io.dynaload.annotations.DynaloadStart;
import io.dynaload.init.DynaloadAutoBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@DynaloadStart(port = 9999, basePackage = "io.dynaload.example.spring.server")
public class DynaloadSpringServerExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynaloadSpringServerExampleApplication.class, args);
        //DynaloadAutoBootstrap.init();
    }

}
