package jdev.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import jdev.dto.TrackDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TrackerController {

    private static final Logger log = LoggerFactory.getLogger(TrackerController.class);

    @PostMapping(value = "/upload")
    public void receiveWrappedTrackingList(@RequestBody TrackDTO track)
            throws JsonProcessingException {

        extractJsonPoints(track.getTrack()).forEach(log::info);
    }

    private List<String> extractJsonPoints(ArrayList<PointDTO> points) throws JsonProcessingException {
        List<String> pointList = new ArrayList<>();
        for (PointDTO p: points)
            pointList.add(p.toJson());
        return pointList;
    }
}
