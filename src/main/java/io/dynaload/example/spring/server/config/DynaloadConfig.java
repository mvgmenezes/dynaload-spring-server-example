//package io.dynaload.example.spring.server.config;
//
//import io.dynaload.Dynaload;
//import io.dynaload.init.DynaloadAutoBootstrap;
//import jakarta.annotation.PostConstruct;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//@EnableConfigurationProperties(DynaloadProperties.class)
//public class DynaloadConfig {
//
//    private final DynaloadProperties properties;
//
//    public DynaloadConfig(DynaloadProperties properties) {
//        this.properties = properties;
//    }
//
//    @PostConstruct
//    public void startDynaloadServer() {
//        Dynaload.start(properties.getPort(), properties.getBasePackage());
//    }
//}
