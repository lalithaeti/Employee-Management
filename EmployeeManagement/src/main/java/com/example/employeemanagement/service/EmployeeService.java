package com.example.employeemanagement.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.TaxDeduction;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Employee save(Employee employee) {
		TaxDeduction taxDeduction = new TaxDeduction();
		employee.setTaxDeduction(taxDeduction);
		return employeeRepository.save(employee);
	}

	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id);
	}

	public void delete(Employee employee) {
		employeeRepository.delete(employee);

	}

	public Employee getTaxDeduction(Long id) {
		Employee updatedEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		TaxDeduction taxDeduction =new TaxDeduction();
		Double yearlySalary = null, taxAmount = null, cessAmount = null;
		
		//calculation for monthe between the DOJ and yearend
		final Calendar d1 = Calendar.getInstance();
		d1.setTime(updatedEmployee.getDoj());
		final Calendar d2 = Calendar.getInstance();
		d2.set(Calendar.YEAR, 2024);
		d2.set(Calendar.MONTH, 3);
		d2.set(Calendar.DATE, 1);
		int monthsBetween = (d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR)) * 12 + d2.get(Calendar.MONTH)
				- d1.get(Calendar.MONTH);

		System.out.println("monthsBetween" + monthsBetween);		
		//calculation of yearly salary
		if (monthsBetween >= 12)
			yearlySalary = updatedEmployee.getSalary() * 12;
		else
			yearlySalary = updatedEmployee.getSalary() * monthsBetween;
		System.out.println("yearlySalary" + yearlySalary);
		if (yearlySalary <= 250000) {
			taxAmount = (double) 0;
			cessAmount = (double) 0;
		}

		else if (yearlySalary > 250000 && yearlySalary <= 500000) {
			taxAmount = 0.05 * (yearlySalary - 250000);
			cessAmount = (double) 0;
		}

		else if (yearlySalary > 500000 && yearlySalary <= 1000000) {
			taxAmount = (0.1 * (yearlySalary - 500000)) + (0.05 * 250000);
			cessAmount = (double) 0;
		}

		else if (yearlySalary > 1000000) {
			if(yearlySalary > 2500000) {
				cessAmount = (0.02 * (yearlySalary - 2500000));
			}
			else {
				cessAmount = (double) 0;
			}
			taxAmount = (0.2 * (yearlySalary - 1000000)) + (0.1 * 500000) + (0.05 * 250000)+ cessAmount;

		}

		System.out.println("yearlySalary" + taxAmount);
		taxDeduction.setCessAmount(cessAmount);
		taxDeduction.setTaxAmount(taxAmount);
		taxDeduction.setYearlySalary(yearlySalary);
		updatedEmployee.setTaxDeduction(taxDeduction);
		return employeeRepository.save(updatedEmployee);

	}

}
