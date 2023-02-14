package com.loeaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication()
@PropertySources({
        @PropertySource("classpath:application-local.properties")
})
public class RstMeetApplication {

    public static void main(String[] args) {
        SpringApplication.run(RstMeetApplication.class, args);
    }

}
