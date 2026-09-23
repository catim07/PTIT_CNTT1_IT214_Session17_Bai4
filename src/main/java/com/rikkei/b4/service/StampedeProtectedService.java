package com.rikkei.b4.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StampedeProtectedService {
    private final AtomicInteger dbQueryCount = new AtomicInteger(0);

    @Cacheable(value = "heavyData", key = "#id", sync = true)
    public String getHeavyData(String id) {
        dbQueryCount.incrementAndGet();
        try {
            Thread.sleep(50); // Simulate heavy operation
        } catch (InterruptedException ignored) {}
        return "HeavyResult_" + id;
    }

    public int getDbQueryCount() {
        return dbQueryCount.get();
    }
}
