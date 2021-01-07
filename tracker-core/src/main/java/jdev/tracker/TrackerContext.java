package jdev.tracker;

import jdev.tracker.services.DispatchService;
import jdev.tracker.services.GpsService;
import jdev.tracker.services.StoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
@PropertySource("classpath:/tracker-core.properties")
public class TrackerContext {

    @Bean
    public GpsService gpsService() {
        return new GpsService();
    }

    @Bean
    public DispatchService dispatchService() {
        return new DispatchService();
    }

    @Bean
    public StoreService storeService() {
        return new StoreService();
    }

    @Bean
    public KmlParser kmlParser() { return new KmlParser(); }

}
