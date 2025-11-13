package com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dto.EmployeeDTO;
import com.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping({"/login","/"})
	public String callLoginpage() {
		return "login";
		//return "Hello from OpenShift and Spring Boot! Database is connected.";
	}

//	@GetMapping("/register")
//	public String registrationPage() {
//
//		return "employeeRegistration";                        //  /WEB-INF/pages       employeeRegistration.html
//	}

	@GetMapping("/register") // Or whatever path loads the form
	public String registrationPage(Model model) {
		// CRITICAL FIX: Initialize and add the required DTO object to the Model
		model.addAttribute("employeeDTO", new EmployeeDTO());

		// This is the correct template name Thymeleaf is looking for
		return "employeeRegistration";
	}


	@PostMapping("/registration")
	public String registerEmployee(@ModelAttribute EmployeeDTO employeeDTO,Model model) {
		    employeeService.registerEmp(employeeDTO);
	
		    model.addAttribute("message", "Record inserted successfully");
		return "registrationStatus";
	}
	
	@PostMapping("/loginValidate")
	public String authenticate(@RequestParam String emailId,@RequestParam String password, Model model) {
		EmployeeDTO	employeeDTO=employeeService.authenticate(emailId,password);
		
		if(employeeDTO!=null) {
			//correct userId+password
			model.addAttribute("employeeDTO", employeeDTO);
		  return "congratulation";	
		} else {
			//wrong credential 
			
			model.addAttribute("message", "Wrong credential--Re-Try please!!!");
			return "login";
		}	
	}

	//fetch all records
	@GetMapping("/showEmployee")
	public String showAllEmployees(Model model) {
		// 1. Fetch data from the Service layer
		List<EmployeeDTO> employeeDtoList=employeeService.findAllEmployees();

		// 2. CRITICAL FIX: Ensure the list is not null before adding it to the Model
		if (employeeDtoList == null) {
			// Initialize an empty ArrayList if the service returns null
			employeeDtoList = new ArrayList<>();
		}
		
		model.addAttribute("employeeDtoList", employeeDtoList);
		return "showAll";
	}
	
	//delete a record
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam int employeeId,Model model) {
		    employeeService.deleteEmp(employeeId);
		
		model.addAttribute("msg", "Record delete successfully");
		
		//return "info";
		
		return "redirect:/showEmployee";
	}
	
	
	
	
	
	
	
}
