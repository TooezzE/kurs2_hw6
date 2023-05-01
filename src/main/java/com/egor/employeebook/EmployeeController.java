package com.egor.employeebook;

import com.egor.employeebook.exceptions.EmployeeAlreadyAddedException;
import com.egor.employeebook.exceptions.EmployeeNotFoundException;
import com.egor.employeebook.exceptions.EmployeeStorageIsFullException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            employeeService.addEmployee(firstName, lastName);
            return "Сотрудник " + firstName + " " + lastName + " добавлен";
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
            employeeService.removeEmployee(firstName, lastName);
            return "Сотрудник " + firstName + " " + lastName + " удален";
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
    public String[] printEmployees(){
        return  employeeService.printEmployees();
    }

}
