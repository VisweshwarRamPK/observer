package com.example.jdbc.service;

import com.example.jdbc.model.Employee;
import com.example.jdbc.observer.EmployeeObserver;
import com.example.jdbc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private final List<EmployeeObserver> observers = new ArrayList<>();

    public void registerObserver(EmployeeObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(EmployeeObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String action, long id) {
        for (EmployeeObserver observer : observers) {
            switch (action) {
                case "create":
                    observer.onEmployeeCreated(id);
                    break;
                case "update":
                    observer.onEmployeeUpdated(id);
                    break;
                case "delete":
                    observer.onEmployeeDeleted(id);
                    break;
            }
        }
    }

    public String createEmployee(Employee employee) {
        String result = employeeRepository.createEmployee(employee);
        if (result.equals("Employee created successfully")) {
            notifyObservers("create", employee.getId());
        }
        return result;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public String updateEmployee(Long id, Employee employee) {
        String result = employeeRepository.updateEmployee(id, employee);
        if (result.equals("Employee updated successfully")) {
            notifyObservers("update", id);
        }
        return result;
    }

    public String deleteEmployee(Long id) {
        String result = employeeRepository.deleteEmployee(id);
        if (result.equals("Employee deleted successfully")) {
            notifyObservers("delete", id);
        }
        return result;
    }
}
