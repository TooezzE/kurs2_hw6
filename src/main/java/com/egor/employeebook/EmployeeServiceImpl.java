package com.egor.employeebook;

import com.egor.employeebook.exceptions.EmployeeAlreadyAddedException;
import com.egor.employeebook.exceptions.EmployeeNotFoundException;
import com.egor.employeebook.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employeeList = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName){
        Employee employee = null;
        if(employeeList.size() >= 10){
            throw new EmployeeStorageIsFullException("Нельзя добавить сотрудника. Коллекция переполнена");
        }
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getFirstName().equals(firstName) && employeeList.get(i).getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Сотрудник с таким именем уже есть в коллекции");
            }
        }
        employee = new Employee(firstName, lastName);
        employeeList.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = null;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getFirstName().equals(firstName) && employeeList.get(i).getLastName().equals(lastName)) {
                employee = employeeList.get(i);
            }
        }
        if (employee == null){
            throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
        } else {
            employeeList.remove(employee);
            return employee;
        }
    }
    public Employee findEmployee(String firstName, String lastName){
        Employee employee = null;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getFirstName().equals(firstName) && employeeList.get(i).getLastName().equals(lastName)) {
                employee = employeeList.get(i);
            }
        }
        if (employee == null){
            throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
        }
        return employee;
    }
}
