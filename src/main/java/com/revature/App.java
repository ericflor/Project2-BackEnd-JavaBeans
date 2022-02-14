package com.revature;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
            SpringApplication.run(App.class, args);
    }

}

