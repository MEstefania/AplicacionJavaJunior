package org.sofkajavajunior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.sofkajavajunior")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}