package com.codegym.config;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class MySQLAbandonedConnectionCleanup {

    @PreDestroy
    public void cleanUp() {
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}