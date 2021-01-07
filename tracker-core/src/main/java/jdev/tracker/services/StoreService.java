package jdev.tracker.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Service//("scheduledStorageService")
public class StoreService {

    private final BlockingDeque<String> storage = new LinkedBlockingDeque<>();

    public void put(String point) throws InterruptedException {
        storage.put(point);
    }

    public List<String> takeAll() throws InterruptedException {
        List<String> messageArray = new ArrayList<>();
        while (!storage.isEmpty()) {
            messageArray.add(storage.takeFirst());
        }
//        messageArray.addAll(storage);
//        storage.clear();
        return messageArray;
    }
}
