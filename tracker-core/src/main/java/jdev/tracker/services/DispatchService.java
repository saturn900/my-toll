package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import jdev.dto.TrackDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DispatchService {

    private static final Logger log = LoggerFactory.getLogger(DispatchService.class);
    private static final String uri = "http://localhost:8080/upload";

    @Autowired
    private StoreService storeService;

    private RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRateString = "${sendingPeriod.prop}",
            initialDelayString = "${initialDelay.prop}")
    public void takeAll() throws InterruptedException, JsonProcessingException {
        send(storeService.takeAll());
    }

    public void send(ArrayList<PointDTO> points) throws JsonProcessingException {

        if (!points.isEmpty()) {
            log.info("___Dispatching " + points.size() + " track points:");

            HttpHeaders headers = new HttpHeaders();
            TrackDTO td = new TrackDTO(points);
            HttpEntity<TrackDTO> requestEntity = new HttpEntity<>(td, headers);

//            restTemplate.postForObject(uri, td, String.class);
//            restTemplate.postForObject(uri, requestEntity, String.class);
//            restTemplate.postForLocation(uri, td);
//            restTemplate.postForLocation(uri, requestEntity);
//            restTemplate.postForEntity(uri, td, String.class);
//            restTemplate.postForEntity(uri, requestEntity, String.class);
//            restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
            ResponseEntity<TrackDTO> response =
                    restTemplate.exchange(uri, HttpMethod.POST, requestEntity, TrackDTO.class);

            log.info(response.getBody().toJson());

            extractJsonPoints(points).forEach(log::info);
        }
    }

        private List<String> extractJsonPoints(ArrayList<PointDTO> points) throws JsonProcessingException {
            List<String> pointList = new ArrayList<>();
            for (PointDTO p: points)
                pointList.add(p.toJson());
            return pointList;
        }
    }
