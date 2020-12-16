package jdev.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class PointDTO {
    private double lat;
    private double lon;
    private String autoId;
    private long time;

    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAutoId() {
        return autoId;
    }
    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public void setThis (PointDTO dto) {
        setLat      (dto.lat);
        setLon      (dto.lon);
        setAutoId   (dto.autoId);
        setTime     (dto.time);
    }

    public void fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            PointDTO dto = mapper.readValue(json, PointDTO.class);
            setThis(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "PointDTO{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", autoId='" + autoId + '\'' +
                '}';
    }

    public void setTime(long time) {
        this.time = time;
    }
    public long getTime() {
        return time;
    }
}