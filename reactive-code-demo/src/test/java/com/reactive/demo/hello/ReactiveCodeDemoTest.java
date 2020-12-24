package com.reactive.demo.hello;

import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReactiveCodeDemoTest {

    // Spring Boot will create a `WebTestClient` for you,
    // already configure and ready to issue requests against "localhost:RANDOM_PORT"
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testHello() {
        webTestClient
                // Create a GET request to test an endpoint
                .get().uri("/hello")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, Spring!");

    }

    @Test
    public void test(){
        Flux.generate(sink -> {
            sink.next("Echo");
            sink.complete();
        }).subscribe(System.out::println);
    }


    @Test
    public void testCreate(){
        Flux.create(sink -> {
            for (char i = 'a'; i <= 'z'; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::print);

        Mono.fromSupplier(() -> "Mono1").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Mono2")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Mono3")).subscribe(System.out::println);


        Flux.merge(Flux.interval(
                Duration.of(0, ChronoUnit.MILLIS),
                Duration.of(100, ChronoUnit.MILLIS)).take(2),
                Flux.interval(
                        Duration.of(50, ChronoUnit.MILLIS),
                        Duration.of(100, ChronoUnit.MILLIS)).take(2))
                .toStream()
                .forEach(System.out::println);
        System.out.println("---");
        Flux.mergeSequential(Flux.interval(
                Duration.of(0, ChronoUnit.MILLIS),
                Duration.of(100, ChronoUnit.MILLIS)).take(2),
                Flux.interval(
                        Duration.of(50, ChronoUnit.MILLIS),
                        Duration.of(100, ChronoUnit.MILLIS)).take(2))
                .toStream()
                .forEach(System.out::println);

        System.out.println("~~~");
        Flux.just(1, 2)
                .flatMap(x -> Flux.interval(Duration.of(x * 10, ChronoUnit.MILLIS),
                        Duration.of(10, ChronoUnit.MILLIS)).take(x))
                .toStream()
                .forEach(System.out::println);
    }


    @Test
    public void test1(){
        Flux.range(1, 10)
                .timeout(Flux.never(), v -> Flux.never())
                .subscribe(System.out::println);

//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .onErrorResume(Mono.just(0))
//                .subscribe(System.out::println);
    }
}