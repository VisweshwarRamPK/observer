package com.example.jdbc.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeLogger implements EmployeeObserver {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeLogger.class);

    @Override
    public void onEmployeeCreated(long id) {
        logger.info("Employee with ID {} created.", id);
    }

    @Override
    public void onEmployeeUpdated(long id) {
        logger.info("Employee with ID {} updated.", id);
    }

    @Override
    public void onEmployeeDeleted(long id) {
        logger.info("Employee with ID {} deleted.", id);
    }
}
