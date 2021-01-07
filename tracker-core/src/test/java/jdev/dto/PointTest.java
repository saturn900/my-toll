package jdev.dto;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class PointTest {

    @Test
    public void toJson() throws Exception {
        Point point = new Point();
        point.setLat(String.valueOf(56));
        point.setLon(String.valueOf(74));
        point.setAutoId("o567gfd");
        assertTrue(point.toJson().contains("\"lat\":56"));
        System.out.println(point.toJson());
    }
}