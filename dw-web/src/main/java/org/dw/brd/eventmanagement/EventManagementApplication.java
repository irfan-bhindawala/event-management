package org.dw.brd.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.dw.brd.eventmanagement.web", "org.dw.brd.eventmanagement.service"})
@EnableJpaRepositories(basePackages = "org.dw.brd.eventmanagement.persistence.repository")
//@EntityScan(basePackages = "org.dw.brd.eventmanagement.persistence.model") <<- Ideally this should be here, enabling it gives "Not a managed type" error. Need to investigate
public class EventManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagementApplication.class, args);
    }
}