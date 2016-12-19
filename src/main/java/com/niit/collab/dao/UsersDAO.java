package com.niit.collab.dao;

import java.util.List;

import com.niit.collab.model.Users;

public interface UsersDAO
{

	public boolean save(Users users);
	public boolean update(Users users);
	public boolean delete(Users users);
	public Users validate(String id,String password);
	public Users oneuser(String id);
	public List<Users> list();
	public List<Users> getuser(String id);
	public Users profileof(String username);
	public Users logout(String id);
	public Users authuser(String username,String password);
	
	
}