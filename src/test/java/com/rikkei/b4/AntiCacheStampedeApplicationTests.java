package com.rikkei.b4;

import com.rikkei.b4.service.StampedeProtectedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AntiCacheStampedeApplicationTests {

    @Autowired
    private StampedeProtectedService service;

    @Test
    void testAntiCacheStampedeWithSyncTrue() throws InterruptedException {
        int threads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                try {
                    service.getHeavyData("ITEM_X");
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        // sync = true guarantees single execution despite concurrent calls
        assertEquals(1, service.getDbQueryCount());
    }
}
