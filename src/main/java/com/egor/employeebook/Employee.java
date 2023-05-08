package com.egor.employeebook;

import java.util.Map;
import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private Integer id = 1;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id++;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }


    @Override
    public String toString() {
        return " Сотрудник {" +
                "Имя = '" + firstName + '\'' +
                ", Фамилия = '" + lastName + '\'' +
                "} ";
    }
}
