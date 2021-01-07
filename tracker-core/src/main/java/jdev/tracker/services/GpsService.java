package jdev.tracker.services;

import jdev.tracker.KmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GpsService {

    @Autowired
    private StoreService storeService;

    @Autowired
    private KmlParser kmlParser;

    @Scheduled(fixedRateString = "${gettingPeriod.prop}")
    public void sendPoint() throws Exception {
        storeService.put(kmlParser.getNext().toJson());
    }

}
