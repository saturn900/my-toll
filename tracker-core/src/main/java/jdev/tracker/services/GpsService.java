package jdev.tracker.services;

import jdev.dto.PointDTO;
import jdev.tracker.KmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Service
public class GpsService {

    @Autowired
    private StoreService storeService;

    @Autowired
    private KmlParser kmlParser;

    @Scheduled(fixedRateString = "${gettingPeriod.prop}")
    public void sendPoint() throws Exception {
        PointDTO nextPoint = kmlParser.getNext();
        if (nextPoint != null)
            storeService.put(nextPoint);
    }

//    getPoint() mock
//
//        @Scheduled(fixedRateString = "${gettingPeriod.prop}")
//        public void getPoint() throws Exception {
//            PointDTO point = new PointDTO(
//                    "213.1",
//                    "565.8",
//                    "F777EE7H",
//                    System.currentTimeMillis());
//            sendPoint(point);
//        }
//
//        void sendPoint(PointDTO point) throws Exception {
//            storeService.put(point);
//        }

}
