package jdev.tracker;

import jdev.dto.PointDTO;

public class Main {
    public static void main(String... args) throws Exception {
        for (int i=0; i<3; i++) {
            System.out.println("Main.main say Hello!!!!");
            PointDTO point = new PointDTO();
            point.setLat(45);
            point.setTime(System.currentTimeMillis());
            System.out.println(point.toJson());
            Thread.sleep(1000);
        }
    }
}
