package jdev.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class TrackDTO {

    private ArrayList<PointDTO> track;

    public ArrayList<PointDTO> getTrack() {
        return track;
    }
    public void setTrack(ArrayList<PointDTO> track) {
        this.track = track;
    }

    public TrackDTO(ArrayList<PointDTO> track) {
        this.track = track;
    }

    public TrackDTO() {}

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
