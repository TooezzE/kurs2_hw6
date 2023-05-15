package com.egor.employeebook;

import com.egor.employeebook.exceptions.EmployeeAlreadyAddedException;
import com.egor.employeebook.exceptions.EmployeeNotFoundException;
import com.egor.employeebook.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees = new ArrayList<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, int departamentId, int salary){
        Employee employee = null;
        if(employees.size() >= 10){
            throw new EmployeeStorageIsFullException("Нельзя добавить сотрудника. Коллекция переполнена");
        }
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Сотрудник с таким именем уже есть в коллекции");
            }
        }
        employee = new Employee(firstName, lastName, salary, departamentId);
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                employee = employees.get(i);
            }
        }
        if (employee == null){
            throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
        } else {
            employees.remove(employee);
        }
        return employee;
    }


    @Override
    public Employee findEmployee(String firstName, String lastName){
        Employee employee = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                employee = employees.get(i);
            }
        }
        if (employee == null){
            throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
        }
        return employee;
    }

    @Override
    public Employee salaryMinInDepartament(int departament) {
        return employees.stream()
                .max(Comparator.comparing(e -> e.getSalary()))
                .get();
    }

    @Override
    public Employee salaryMaxInDepartament(int departament) {
        return employees.stream()
                .min(Comparator.comparing(e -> e.getSalary()))
                .get();
    }
    @Override
    public String printEmployees() {
        return employees.toString();
    }
}
