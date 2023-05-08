package com.egor.employeebook;

import java.util.Collection;
import java.util.Collections;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    String printEmployees();
}
