package com.example.model;

import java.util.Objects;

public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private int age;
    private Functions functions;

    public Employee(){

    }
    public Employee(Integer id, String firstName, String lastName, int age, Functions functions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.functions = functions;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Functions getFunction() { return functions; }
    public void setFunction(Functions functions) { this.functions = functions; }

    @Override
    public String toString() {
        return "{ id: " + id + ",\n" +
                " fisrtName: " + firstName + ",\n" +
                " lastName: " + lastName + ",\n" +
                " age: " + age + ",\n" +
                " function: { name: " + functions.getName() +
                " roles: " + functions.getRoles() + ",\n" +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}