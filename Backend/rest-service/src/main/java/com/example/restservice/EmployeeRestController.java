package com.example.restservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Employee;
import com.example.model.Functions;
import com.example.utils.FileOperations;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeRestController {

    @GetMapping("/creatingList")
    public void creatingList() {
        List<Employee> employeeList = new ArrayList<>();

        Functions managerFunctions = new Functions();
        managerFunctions.setName("Manager");
        managerFunctions.setManagerRoles();
        Employee employee = new Employee(0, "Maria", "Aldes", 20, managerFunctions);
        employeeList.add(employee);

        Functions developerFunctions = new Functions();
        developerFunctions.setName("Developer");
        developerFunctions.setDeveloperRoles();
        employee = new Employee(1, "Sergiu", "Idu", 21, developerFunctions);
        employeeList.add(employee);

        Functions accountingFunctions = new Functions();
        accountingFunctions.setName("Accounting");
        accountingFunctions.setAccountingRoles();
        employee = new Employee(2, "Ioana", "Popescu", 30, accountingFunctions);
        employeeList.add(employee);

        Functions hrFunctions = new Functions();
        hrFunctions.setName("HumanResources");
        hrFunctions.setHumanResourcesRoles();
        employee = new Employee(3, "Ana", "Gheorghe", 27, hrFunctions);
        employeeList.add(employee);

        Functions marketingFunctions = new Functions();
        marketingFunctions.setName("Marketing");
        marketingFunctions.setMarketingRoles();
        employee = new Employee(4, "Mara", "Popa", 22, marketingFunctions);
        employeeList.add(employee);

        employee = new Employee(5, "Andreea", "Olari", 19, developerFunctions);
        employeeList.add(employee);

        FileOperations file = new FileOperations();
        Gson gson = new Gson();
        file.write(gson.toJson(employeeList).toString());

        System.out.println("Employee list ok!");
    }

    private List<Employee> getEmployeeList() throws IOException {
        Reader reader = getReader("src/persistence/employees.json");
        List<Employee> employeeList = new Gson().fromJson(reader, new TypeToken<List<Employee>>() {
        }.getType());
        closeReader(reader);

        return employeeList;
    }

    private void closeReader(Reader reader) throws IOException {
        reader.close();
    }

    private BufferedReader getReader(String PathToFile) throws IOException {
        return Files.newBufferedReader(Paths.get(PathToFile));
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() throws IOException {
        List<Employee> employeeList = getEmployeeList();

        employeeList.forEach(System.out::println);
        return employeeList;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getById(@PathVariable Integer employeeId) throws IOException {
        List<Employee> employeeList = getEmployeeList();

        System.out.println(employeeList.get(employeeId));

        return employeeList.get(employeeId);
    }

    @PostMapping("/employees/add")
    public void add(@RequestBody Employee employee) throws IOException {

        List<Employee> employeeList = getEmployeeList();
        employeeList.add(employee);

        FileOperations file = new FileOperations();
        Gson gson = new Gson();
        file.write(gson.toJson(employeeList));

        System.out.println("Saving employee information");
        employeeList.forEach(System.out::println);
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@RequestBody Employee employee) throws IOException {

        List<Employee> employeeList = getEmployeeList();

        Functions functions = employee.getFunction();
        employeeList.get(employee.getId()).setFunction(functions);

        FileOperations file = new FileOperations();
        Gson gson = new Gson();
        file.write(gson.toJson(employeeList));

        System.out.println(employeeList.get(employee.getId()));

        return employeeList.get(employee.getId());
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployeeById(@PathVariable Integer employeeId) throws IOException {

        List<Employee> employeeList = getEmployeeList();
        System.out.println("Deleted employee: " + employeeList.get(employeeId));
        employeeList.removeIf(employee -> employee.getId().equals(employeeId));

        FileOperations file = new FileOperations();
        Gson gson = new Gson();
        file.write(gson.toJson(employeeList));

        employeeList.forEach(System.out::println);
    }
}