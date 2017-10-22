package org.dw.brd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.dw")
public class ApplicationBootStart {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationBootStart.class, args);
  }
}
