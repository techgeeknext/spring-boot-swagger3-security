package com.techgeeknext.controller;

import com.techgeeknext.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "techgeeknext-api")
public class UserController {

	List<User> users = new ArrayList<User>();
	{
		users.add(new User(1,"TechGeekNext-User1", "ADMIN", "user1@test.com"));
		users.add(new User(2,"TechGeekNext-User2", "SUPERVISOR", "user2@test.com"));
		users.add(new User(3,"TechGeekNext-User3", "USER", "user3@test.com"));
		users.add(new User(4,"TechGeekNext-User4", "USER", "user4@test.com"));
	}


	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUsers() {
		return users;
	}

	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, produces = "application/json")
	public User getUserById(@PathVariable(value = "id") int id) {
		return users.stream().filter(x -> x.getId()==(id)).collect(Collectors.toList()).get(0);
	}

	@RequestMapping(value = "/getUser/role/{role}", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserByRole(@PathVariable(value = "role") String role) {
		return users.stream().filter(x -> x.getRole().equalsIgnoreCase(role))
				.collect(Collectors.toList());
	}
}