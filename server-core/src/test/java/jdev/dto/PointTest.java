package jdev.dto;

import org.junit.Test;
    import static org.junit.Assert.assertTrue;

public class PointTest {

    @Test
    public void toJson() throws Exception {
        Point point = new Point("56.0", "74.0", "o567gfd", System.currentTimeMillis());
        assertTrue(point.toJson().contains("\"lat\":\"56.0\""));
        System.out.println(point.toJson());
    }
}