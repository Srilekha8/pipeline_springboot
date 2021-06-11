package com.ashwin.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashwin.accessingdatamysql.Details;
import com.ashwin.accessingdatamysql.DetailsRepository;
import com.ashwin.accessingdatamysql.User;
import com.ashwin.accessingdatamysql.UserRepository;

@Controller
public class GreetingController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	@Autowired
	private DetailsRepository detailsRepository;

	@PostMapping("/login")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			@RequestParam(name = "email", required = false, defaultValue = "s@gmail.com") String email, Model model) {


		// putting the name and email in the database spring_db
		User userDetails = new User();
		userDetails.setName(name);
		userDetails.setEmail(email);
		userRepository.save(userDetails);
		
		// setting the name and email to the Model greeting.html
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		System.out.println("Calling the /greeting get method.");
		// add name and email to database spring_db
		return "userdetails";
	}
	@PostMapping("/details")
	public String details(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "email", required = true) String email, 
			@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "suranme", required = true) String surname,
			@RequestParam(name = "age", required = true) int age,
			@RequestParam(name = "year", required = true) int year,			
			Model model) {

		// putting the name and email in the database spring_db
		Details details = new Details();
		details.setName(name);
		details.setEmail(email);
		details.setFirstName(firstName);
		details.setSurname(surname);
		details.setAge(age);
		details.setYear(year);
		
		detailsRepository.save(details);
		
		System.out.println("Calling the /greeting get method.");
		// add name and email to database spring_db
		model.addAttribute("userDetails", detailsRepository.findAll());
		
		// return all the name and email values from spring_db
		return "viewall";
	}

	@GetMapping("/viewall")
	public String viewall(Model model) {

		model.addAttribute("userDetails", userRepository.findAll());
	
		// return all the name and email values from spring_db
		return "viewall";
	}
	

}