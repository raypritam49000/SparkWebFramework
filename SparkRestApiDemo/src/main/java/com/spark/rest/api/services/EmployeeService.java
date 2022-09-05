package com.spark.rest.api.services;
import java.util.List;

import com.spark.rest.api.entity.Employee;

public interface EmployeeService {

    public Employee addEmployee(Employee employee);
    public List<Employee> getEmployees();
    public Employee getEmployee(Long id);
    public Employee  updateEmployee(Long id,Employee updateEmployee);
    public void deleteEmployee(Long id);
    public boolean employeeExist(Long id);
}