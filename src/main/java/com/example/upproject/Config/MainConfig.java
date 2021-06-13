package com.example.upproject.Config;

import com.example.upproject.DataBase.BaseConnection;
import com.example.upproject.DataBase.BaseInteractions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class MainConfig {
    @Bean("connection")
    public BaseConnection Connector() {
        return new BaseConnection();
    }
    @Bean("executant")
    public BaseInteractions Executant() {
        return new BaseInteractions();
    }

}
