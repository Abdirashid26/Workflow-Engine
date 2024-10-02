package com.faisaldev.workflow_engine.configs;


import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfigs {


    @Bean
    public Gson provideGson(){
        return new Gson();
    }



}
