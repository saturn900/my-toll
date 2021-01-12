package jdev.tracker.app;

import jdev.tracker.TrackerContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TrackerMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TrackerContext.class);
    }

}

//@SpringBootApplication
//@ComponentScan({"jdev/tracker", "jdev/tracker/services"})
//public class TrackerMain {
//
//    public static void main(String[] args) {
//        SpringApplication.run(TrackerMain.class, args);
//    }
//
//}