package com.egor.employeebook;

import com.egor.employeebook.exceptions.EmployeeAlreadyAddedException;
import com.egor.employeebook.exceptions.EmployeeNotFoundException;
import com.egor.employeebook.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String, Employee> employees = new HashMap<>();

@Override
    public Employee addEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if(employees.containsKey(employee.getFullName())){
            throw new EmployeeStorageIsFullException("Нельзя добавить сотрудника. Коллекция переполнена");
        }

        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
            if(employee == null){
                throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
            } else {
                employees.remove(employee.getFullName());
        }
        return employee;
    }


    @Override
    public Employee findEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
            if(employees.containsKey(employee.getFullName())){
                return employee;
            } else {
                throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
            }
    }

    @Override
    public String printEmployees() {
        return employees.values().toString();
    }
}
