package com.example.jdbc.observer;

public interface EmployeeObserver {
    void onEmployeeCreated(long id);
    void onEmployeeUpdated(long id);
    void onEmployeeDeleted(long id);
}
