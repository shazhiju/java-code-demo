package com.reactive.demo;


import com.reactive.demo.hello.GreetingWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveCodeApplication.class, args);

        GreetingWebClient gwc = new GreetingWebClient();
        System.out.println(gwc.getResult());

    }
}
