package com.cydeo;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync(proxyTargetClass=true)
public class TicketingProjectRest {
    public static void main(String[] args) {
        SpringApplication.run(TicketingProjectRest.class, args);
    }
    @Bean
    public ModelMapper mapper(){

        return new ModelMapper();
    }
    @Bean
    public SimpleMailMessage getMailMessage(){
        return new SimpleMailMessage();
    }

//    @Bean
//    public PasswordEncoder getEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
