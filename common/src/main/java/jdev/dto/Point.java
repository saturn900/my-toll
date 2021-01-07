package jdev.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Point {

    private String lat;
    private String lon;
    private String autoId;
    private long time;

    public Point(String lat, String lon, String autoId, long time) {
        this.autoId = autoId;
        this.lat    = lat;
        this.lon    = lon;
        this.time   = time;
    }

    public Point(Point point) {
        setPoint(point);
    }

    public void setPoint(Point point) {
        setLat      (point.lat);
        setLon      (point.lon);
        setAutoId   (point.autoId);
        setTime     (point.time);
    }

    public Point() {
    }

    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAutoId() {
        return autoId;
    }
    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public void setTime(long time) {
        this.time = time;
    }
    public long getTime() {
        return time;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public void fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Point dto = mapper.readValue(json, Point.class);
        setPoint(dto);
    }

    @Override
    public String toString() {
        return "Point{"         +
                "lat='"         + lat    +
                "', lon='"      + lon    +
                "', autoId='"   + autoId +
                "', time="      + time   + "}";
    }

}