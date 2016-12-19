package com.niit.collab.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collab.dao.UsersDAO;
import com.niit.collab.model.Users;

@RestController
public class LoginController {
	@Autowired 
	private UsersDAO usersDAO;

	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<Users> login( @PathVariable("username") String username,@PathVariable("password") String password ,HttpSession session){
		Users users = usersDAO.authuser(username,password);
		if(users==null)
			{	return null;
	}else{
		session.setAttribute("userLogged", users);
		session.setAttribute("uid", users.getId());
		session.setAttribute("username", users.getUsername());
		users.setStatus('o'); //online
		/*usersDAO.save(users);*/
		usersDAO.update(users);
		return new ResponseEntity<Users>(users,HttpStatus.OK);
	}
	}
	@PostMapping("/logout")
	public ResponseEntity<Users> logout(HttpSession session){
		String uid =  (String) session.getAttribute("username");
		Users users =usersDAO.logout(uid);
		users.setStatus('f'); //offline
		/*usersDAO.save(users);*/
		usersDAO.update(users);
		session.invalidate();
		return new ResponseEntity<Users>(users,HttpStatus.OK);
	}
}