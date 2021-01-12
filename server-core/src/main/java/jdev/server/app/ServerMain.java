package jdev.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"jdev/server/controllers"})
public class ServerMain {

    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);
    }

}
