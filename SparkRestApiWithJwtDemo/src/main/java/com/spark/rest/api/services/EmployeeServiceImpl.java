package com.spark.rest.api.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.spark.rest.api.dbconfig.HibernateUtil;
import com.spark.rest.api.entity.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	private Session session = HibernateUtil.getSession();

	@Override
	public Employee addEmployee(Employee emp) {
		Transaction trx = session.beginTransaction();
		Serializable id = session.save(emp);
		trx.commit();
		return session.getReference(Employee.class, id);
	}

	
	@Override
	public List<Employee> getEmployees() {
		return session.createQuery("from Employee").getResultList();
	}

	@Override
	public Employee getEmployee(Long id) {
		return session.get(Employee.class, id);
	}

	@Override
	public Employee updateEmployee(Long id,Employee updateEmployee) {
		Serializable sid = null;
		Employee employee = session.get(Employee.class, id);
		if(employee!=null) {
			employee.setEmail(updateEmployee.getEmail());
			employee.setFirstName(updateEmployee.getFirstName());
			employee.setLastName(updateEmployee.getLastName());
			sid = this.session.save(employee);
		}
		return session.get(Employee.class, sid);
	}

	@Override
	public void deleteEmployee(Long id) {
		Transaction trx = session.beginTransaction();
		session.remove(session.getReference(Employee.class, id));
		trx.commit();
	}

	@Override
	public boolean employeeExist(Long id) {
		return session.contains(session.getReference(Employee.class, id));
	}
}