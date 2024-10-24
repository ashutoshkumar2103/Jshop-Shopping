package com.jshop.jshopspringboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringboot.dto.User;
import com.jshop.jshopspringboot.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Autowired
	private UserRepository repository;
	/*
	 * saveUserDao
	 */
	public User saveUserDao(User user) {
		return repository.save(user);
	}
	
	/*
	 * loginUser
	 */
	public User loginUserDao(String email)
	{
		User user = repository.findByUserEmail(email);
		if(user!=null) {
			setUser(user);
			return user;
		}
		return null;
	}
	
	/*
	 * update user
	 */
	 public User updateUserDao(User userDetails) {
		 User existingUser = repository.findByUserEmail(userDetails.getUserEmail());
		 // Update user details
		 existingUser.setUserName(userDetails.getUserName());
		 existingUser.setUserPassword(userDetails.getUserPassword());
		 existingUser.setUserPhone(userDetails.getUserPhone());
		 // Add more fields as needed

		 // Save updated user
		 User updatedUser = repository.save(existingUser);
		 return updatedUser;
	 }
}
