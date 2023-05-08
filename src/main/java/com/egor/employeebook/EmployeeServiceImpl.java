package com.egor.employeebook;

import com.egor.employeebook.exceptions.EmployeeAlreadyAddedException;
import com.egor.employeebook.exceptions.EmployeeNotFoundException;
import com.egor.employeebook.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<Integer, Employee> employeeList = new HashMap<>();

@Override
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
        employeeList.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        if(employee == null){
            throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
        } else {
            employeeList.remove(employee.getId());
        }
        return employee;
    }


    @Override
    public Employee findEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if(employeeList.containsKey(employee.getId())) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
        }
    }

    @Override
    public String[] printEmployees() {
        String[] print = new String[employeeList.size()];
        int i = 0;
        for (Integer key: employeeList.keySet()) {
            String keyCheck = key.toString();
            String employee = employeeList.get(key).toString();
            print[i] = keyCheck + " " + employee;
         }
        return print;
    }
}
