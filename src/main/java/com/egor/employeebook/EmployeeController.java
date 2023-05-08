package com.egor.employeebook;

import com.egor.employeebook.exceptions.EmployeeAlreadyAddedException;
import com.egor.employeebook.exceptions.EmployeeNotFoundException;
import com.egor.employeebook.exceptions.EmployeeStorageIsFullException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        try {
            return "Added " + employeeService.addEmployee(firstName, lastName).toString();
        } catch (EmployeeStorageIsFullException e) {
            return "Невозможно добавить сотрудника. Список переполнен.";
        } catch (EmployeeAlreadyAddedException e) {
            return "Невозможно добавить сотрудника. Сотруник с таким именем уже есть в списке.";
        }
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        try {
            return "Deleted " + employeeService.removeEmployee(firstName, lastName).toString();
        } catch (EmployeeNotFoundException e) {
            return "Сотрудник с именем " + firstName + " " + lastName + " не найден.";
        }
    }

    @GetMapping(path = "find")
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) {
        try {
            return "Результат поиска: " + employeeService.findEmployee(firstName, lastName).toString();
        } catch (EmployeeNotFoundException e) {
            return "Результат поиска: сотрудник с именем " + firstName + " " + lastName + " не найден.";
        }
    }

    @GetMapping(path = "/printAll")
    public String printEmployees(){
        return  employeeService.printEmployees();
    }

}
