package jdev.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PointDTOTest {

    private String expected = "{\"lat\":\"56.0\",\"lon\":74.0,\"autoId\":\"o567gfd\",\"time\":1606631051960}";
    private String autoId = "o567gfd";

    @Test
    public void toJson() throws Exception {
        PointDTO point = new PointDTO("56.0", "74.0", "o567gfd", System.currentTimeMillis());
        assertTrue(point.toJson().contains("\"lat\":\"56.0\""));
        assertTrue(point.toJson().contains("\"time\""));
        System.out.println(point.toString());
        System.out.println(point.toJson());
    }

    @Test
    public void decodeDTO() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PointDTO dto = mapper.readValue(expected, PointDTO.class);
        assertEquals(autoId, dto.getAutoId());
        assertEquals(1606631051960L, dto.getTime());
    }

    @Test
    public void encodeDTO() throws IOException {
        PointDTO dto = new PointDTO();
        dto.fromJson(expected);
        assertEquals(autoId, dto.getAutoId());
//        assertEquals(autoId + "_wrongId", dto.getAutoId());
    }
}