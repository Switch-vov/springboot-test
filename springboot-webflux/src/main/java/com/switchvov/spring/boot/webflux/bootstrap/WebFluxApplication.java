package com.switchvov.spring.boot.webflux.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author switch
 * @since 2020/2/13
 */
@SpringBootApplication
@RestController
public class WebFluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }

    @GetMapping("/mvc")
    public String mvc() {
        println("MVC");
        return "MVC";
    }

    @GetMapping("/mono")
    public Mono<String> mono() {
        println("MONO");
        return Mono.just("Mono");
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
//        return route(
//                request -> "/hello-world".equals(request.uri().getPath()),
//                request -> ServerResponse.status(HttpStatus.OK).body(Mono.just("Hello World"), String.class));
        return route(
                GET("/hello-world"),
                request -> ServerResponse.status(HttpStatus.OK).body(Mono.just("Hello World"), String.class));
    }

    private static void println(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] : " + message);
    }
}
