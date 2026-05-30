package com.example.demo.adapter.configuration;

import com.example.demo.application.service.ManagerUserImpl;
import com.example.demo.domain.ports.in.ManagerUser;
import com.example.demo.domain.ports.out.H2Manager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ManagerUserImpl managerUser(H2Manager h2Manager) {
        return new ManagerUserImpl(h2Manager);
    }

}
