package jdev.tracker.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispatchService {

    private static final Logger log = LoggerFactory.getLogger(DispatchService.class);

    @Autowired
    private StoreService storeService;

    @Scheduled(fixedRateString = "${sendingPeriod.prop}", initialDelayString = "${initialDelay.prop}")
    public void takeAll() throws InterruptedException {
        send(storeService.takeAll());
    }

    public void send(List<String> points) {
        log.info("___Dispatching " + points.size() + " track points:");
        points.forEach(log::info);
    }
}
