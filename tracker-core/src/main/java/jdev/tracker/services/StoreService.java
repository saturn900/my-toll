package jdev.tracker.services;

import jdev.dto.PointDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class StoreService {

    private final BlockingDeque<PointDTO> storage = new LinkedBlockingDeque<>();

    public void put(PointDTO point) throws InterruptedException {
        storage.put(point);
    }

    public ArrayList<PointDTO> takeAll() throws InterruptedException {
        ArrayList<PointDTO> messageArray = new ArrayList<>();
        while (!storage.isEmpty()) {
            messageArray.add(storage.takeFirst());
        }
//        messageArray.addAll(storage);
//        storage.clear();
        return messageArray;
    }
}
