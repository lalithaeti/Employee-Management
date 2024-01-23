package com.example.employeemanagement.entity;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`employees`")
@ToString
@EqualsAndHashCode

public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private long employeeId;
    
	@Size(max = 30, message = "name should be maximum 30 characters")
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Email
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "doj")
	private Date doj;
	
	@Column(name = "salary")
	private Double salary;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_deduction_id")
	private TaxDeduction taxDeduction;
	
	}

